# Initial Project Setup steps

# Connecting to a DB and configuring DB as code

### Pre-requisites
- A docker database image 
- Oracle JDK8 JDBC driver (download from https://www.oracle.com/technetwork/database/features/jdbc/jdbc-ucp-122-3110062.html)

# Creating the docker database image

- Clone https://github.com/oracle/docker-images
- Navigate to `OracleDatabase/SingleInstance`, download the relevant ISO and follow the instructions to create an image.

      $ docker images
      REPOSITORY                         TAG                 IMAGE ID            CREATED             SIZE
      oracle/database                    11.2.0.2-xe         812323abe26c        2 days ago          1.13GB
      oracle/database                    12.2.0.1-se2        bd2090a77285        4 days ago          13.2GB

- Copy the jdbc driver into a Docker mappable directory, e.g. /tmp/drivers
- Create `buildtools/sql` directory and add a `create-user.sql` file to create the user `atg`. This file is available in the container at the mount point `/atg/sql`

- Create the database:

     ```
     docker run --name oracle_db -p 1521:1521 -e ORACLE_PWD=Admin123 --mount source=atgdb_vol,destination=/opt/oracle/oradata -v $(pwd)/buildtools/sql:/atg/sql -v /tmp/drivers:/drivers oracle/database:12.2.0.1-se2
     ```
     
   This creates a container with a re-usable host mounted volume for the database, password override on startup for `sys/dba` and local volume mount to the `buildtools/sql` directory and the jdbc driver at /drivers for running SQL scripts direct from our repository.
   
      
      $ docker volume ls
      DRIVER              VOLUME NAME
      local               atgdb_vol
      $ docker ps -a
      CONTAINER ID        IMAGE                          COMMAND                  CREATED             STATUS                   PORTS                              NAMES
      3bd676bed318        oracle/database:12.2.0.1-se2   "/bin/sh -c 'exec $O…"   5 minutes ago       Up 5 minutes (healthy)   0.0.0.0:1521->1521/tcp, 5500/tcp   oracle_db

- When the database is ready test the connection in a new terminal:

      $ docker exec -it oracle_db bash
      [oracle@3bd676bed318 ~]$ 
      $ sqlplus sys/Admin123@ORCLPDB1 as sysdba
      
      SQL*Plus: Release 12.2.0.1.0 Production on Thu Jul 26 10:39:12 2018
      
      Copyright (c) 1982, 2016, Oracle.  All rights reserved.
      
      
      Connected to:
      Oracle Database 12c Standard Edition Release 12.2.0.1.0 - 64bit Production
      
      SQL> select username from all_users;
      
      USERNAME
      --------------------------------------------------------------------------------
      SYS
      AUDSYS
      SYSTEM
      ...
      
      $ exit


- Create a new single user instance to use to load the ATG DDL for a combined schema:


      [oracle@3bd676bed318 ~]$ sqlplus sys/Admin123@ORCLPDB1 as sysdba @/atg/sql/create-user.sql
      
      SQL*Plus: Release 12.2.0.1.0 Production on Thu Jul 26 10:45:21 2018
      
      Copyright (c) 1982, 2016, Oracle.  All rights reserved.
      
      
      Connected to:
      Oracle Database 12c Standard Edition Release 12.2.0.1.0 - 64bit Production
      
      
      User created.
      
      
      Grant succeeded.
      
      Disconnected from Oracle Database 12c Standard Edition Release 12.2.0.1.0 - 64bit Production
      
      
- From the local installation of ATG run CIM to determine which tables to install.

     `$ATG_HOME/home/bin/cim.sh -record -noencryption`
     
     Follow the prompts to select the default commerce product, install OPSS, etc. The important part is the database connection. Choose the non-switching datasource option.
     
     Set all the JDBC connection properties as per your database setup.  
     In the case of SE2 change the default URL to the following (otherwise it will fail to connect):
     `jdbc:oracle:thin:@//localhost:1521/ORCLPDB1`  
     Test the connection and install the DDL and data.
     You can optionally save the CIM recording, to run again as necessary: See `buildtools/atg/atg_dcs_batch.cim`

- The important part from this exercise was to determine the list of tables matching our modules. This is not recorded in the CIM properties file but is found from the logs at `$ATG_HOME/CIM/log/cim.log`.
  Look for the list of tables created and the data imported. See `buildtools/atg/atg_dcs_ddl`. Also note near the top of the log file the modules used to generate the required DDL list.  
  `Top level module list for Datasource Production Core: DafEar.Admin,DPS,DSS,ContentMgmt,DCS.PublishingAgent,PublishingAgent`.
     
  Now we have the 3 required elements going forward:
  - the list of DDL files to execute
  - the required data to import
  - the list of modules to configure runAssembler with.
  
- Check the number of tables installed for the `atg` user:

  ```bash
  SQL> select table_name from user_tables;
  ...
  DCS_SITE                                                                                                                        
  
  450 rows selected. 

  ```
  
- Take the list of modules and update the runAssembler configuration, e.g:

  ```bash
  runAssembler {
    storefront {
      outputFileName = "ATGStorefront.ear"
      modules = ["DafEar.Admin","DPS","DSS","ContentMgmt","DCS.PublishingAgent","PublishingAgent"]
      jboss = true
    }
  }
  ```
  
- Test the assembly:

  ```bash
  $ g rAS
  
  > Task :runAssembleStorefront
  runAssembler -jboss /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear -m DafEar.Admin DPS DSS ContentMgmt DCS.PublishingAgent PublishingAgent
  Warning: unable to determine Unix variant.  Assuming Solaris.
  The following installed Oracle Commerce components are being used to launch:
    ATGPlatform version 11.3 installed at /opt/ATG/ATG11.3
  
  Assembly started.
  Target application is /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear.
  Creating exploded ear file /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear.
  Initializing...
  Updating /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear.
  Before make meta-inf file.
  Making meta-inf file /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear/META-INF
  Creating application.xml...
  Creating Ear file MANIFEST...
  Importing classes...
  Importing classes for module DafEar
  Importing classes for module DafEar.Admin
  Importing classes for module DafEar.base
  Importing classes for module DCS.PublishingAgent
  Importing classes for module PublishingAgent
  Importing classes for module PublishingAgent.base
  Importing classes for module DCS
  Importing classes for module ContentMgmt
  Importing classes for module DSS
  Importing classes for module DPS
  Importing classes for module DAS-UI
  Importing classes for module DAF.DeploymentAgent
  Importing classes for module DAS
  Ignoring classpath entry lib/servlet.jar from module DAS
  Ignoring classpath entry lib/jsp-api.jar from module DAS
  Importing J2EE module atg-admin.war declared in DafEar.Admin
    Parsing /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear/atg_admin.war/WEB-INF/web.xml
  Importing J2EE module atg-bootstrap.war declared in DafEar.base
    Parsing /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear/atg_bootstrap.war/WEB-INF/web.xml
  Importing J2EE module atg-bootstrap-ejb.jar declared in DafEar.base
  Assembly took 0.920 seconds.
  
  BUILD SUCCESSFUL in 1s
  1 actionable task: 1 executed
  ```
  
- Now we want to automate the loading of the data with an incremental database tool that we would use on a larger scale during the project lifecycle. We could use the CIM tool itself but it has limitations so lets look at a more industry wide used tool such as Flyway or Liquibase. Both are good for what we want but we will choose Liquibase as it has more flexibility for rollback testing, configuration formats and has a larger commercial product upgrade if required. The open-source version has been used successfully on large ATG projects in the past, whereas Flyway has different versions and the free version is a bit limited for our usage.  
  Lets create a new module Liquibase which will contain our build configuration, the DDL and data and any plugin extensions and code.
  
- Create a directory to store the DDL. This can be broken down into ATG module sub-directories if required, so we know where each file was sourced and we can simply copy the files over. See the tree un `Liquibase/install/core/ddl/atg/`.  Using the list of DDLs installed by CIM previously copy over each file from the installation to the relevant directory in the Liquibase module. *Note* that we could just use the `das_ddl.sql` file but as we know later we will need to break up the core schema into the core and the switching schema, so we use the files from the `<ATG module>/sql/dbcomponents/oracle` directory.

- In each lowest level directory add a file named `install.groovy` and add a Liquibase databasechangelog changeset configuration for each sql file.

- In the parent atg directory create another `install.groovy` which includes each lower level directory configuration:
  ```bash
  databaseChangeLog {
    include(file:'Liquibase/install/core/ddl/atg/DAS/install.groovy')
    include(file:'Liquibase/install/core/ddl/atg/DPS/install.groovy')
    include(file:'Liquibase/install/core/ddl/atg/DSS/install.groovy')
    include(file:'Liquibase/install/core/ddl/atg/ContentMgmt/install.groovy')
    include(file:'Liquibase/install/core/ddl/atg/DCS/install.groovy')
  
    changeSet(id:'core_atg_ddl',author:'hallatech',context:'ddl') {
      tagDatabase(tag:'core_atg_ddl')
    }
  
  }
  ```
  
- Finally create a `core-changelog.groovy` at the `Liquibase/install/core` level that includes the lower level file. This will expand later but for now its just the one line:
  ```bash
  databaseChangeLog {
    include(file:'Liquibase/install/core/ddl/atg/install.groovy')
  }
  ```
  
- Now we are ready to introduce the liquibase plugin to the build and configure it to run Liquibase to install the DDL.
  Note: You need to find a relevant JDBC driver either from maven central, jcenter, your internal Nexus/Artifact repo, or local file system. Configure accordingly.
  ```
  repositories {
    jcenter()
  }
  
  dependencies {
    liquibaseRuntime 'org.liquibase:liquibase-core:3.6.1'
    liquibaseRuntime 'org.liquibase:liquibase-groovy-dsl:2.0.0'
    liquibaseRuntime 'com.github.noraui:ojdbc8:12.2.0.1'
  }
  
  liquibase {
    activities {
      core {
        changeLogFile 'Liquibase/install/core/core-changelog.groovy'
        url 'jdbc:oracle:thin:@//localhost:1521/ORCLPDB1'
        username 'atg'
        password 'atg'
      }
    }
  }

  ```
  
- Run `gradle tasks` to see the list of tasks for Liquibase. Run `gradle validate` to validate your initial configuration of the plugin.

- Make sure you have an empty schema to start with, so if necessart, drop and recreate the atg user.

- Run `gradle update` to load the schema. We should now hit a known issue with the DDL:
  ```bash
  $ g update
  
  > Task :update
  liquibase-plugin: Running the 'core' activity...
  Starting Liquibase at Sat, 28 Jul 2018 11:25:15 KST (version 3.6.1 built at 2018-04-11 09:05:04)
  Unexpected error running Liquibase: ORA-00900: invalid SQL statement
   [Failed SQL: Pupdate_count := SQL%ROWCOUNT]
  liquibase.exception.MigrationFailedException: Migration failed for change set Liquibase/install/core/ddl/atg/DAS/install.groovy::core_atg_das_ddl::hallatech:
       Reason: liquibase.exception.DatabaseException: ORA-00900: invalid SQL statement
   [Failed SQL: Pupdate_count := SQL%ROWCOUNT]
  ...
  ```
  There are some procedures in the DDL that need a little more explicit declaration.
  
- Extract the procs from `create_sql_jms_ddl.sql` to its own file in a `procs` directory. Embed the procs in the own changeSets. Update the core changelog to include that file.
  ```bash
  databaseChangeLog {
    include(file:'Liquibase/install/core/ddl/atg/install.groovy')
    include(file:'Liquibase/install/core/procs/atg/install.groovy')
  }
  ```
  
- Running again should result in another error as there are procs defined in `create_cms_procedures.sql`. In the DCS directory set the _splitStatements_ option to false:
  ```bash
  sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/create_cms_procedures.sql',splitStatements:false)
  ```
  This should now produce a successful liquibase installation of the DDL. *Note* you might have to recreate the schema the first time to avoid the re-insert of previous tables.
  Make sure once its all working that you retest from a clean schema.

  ```bash
  $ g update
  
  > Task :update
  liquibase-plugin: Running the 'core' activity...
  Starting Liquibase at Sat, 28 Jul 2018 11:51:59 KST (version 3.6.1 built at 2018-04-11 09:05:04)
  Liquibase: Update has been successful.
  
  BUILD SUCCESSFUL in 7s
  1 actionable task: 1 executed
  ```
  You can also check the Liquibase changelog table:  
  `select id, tag, orderexecuted from databasechangelog order by orderexecuted;`
  
  

  

  
   