# Initial Project Setup steps

### Pre-requisites

- A single project resource can do the initial setup.
- JDK installed locally
- Gradle installed locally (This is only required for the resource creating the initial project setup)
- ATG installed locally (again for the resource creating the initial setup. This can also be done with a Docker image or a Vagrant VM - but for now we haven't decided that yet)
- As a convenience create a bash profile `alias g=./gradlew`. All gradle commands will use this alias going forward. 

### Base setup
1. Create project in GitHub and clone to local
2. In local project (assumes you have `Gradle` installed): `gradle init`. This will allow the project to maintain its own Gradle wrapper and run via the wrapper in CI.
3. Run `g tasks`, to confirm the wrapper is operational.


- We will assume that the application will generate a few EARs depending on the Oracle products required, e.g. a `storefront`, `BCC`,`CSC` and a utility server such as a `lock-server`.
- We will also assume that the project will utilise a number of ATG modules to breakdown the application code base into various re-usable modules and also some utility modules to aid configuration and data management.
- ATG DDL and datasources assume a single schema (development) but should use the Switching datasources and split schemas in managed environments. We assume the single schema/datasource setup first.
- We will start off with a single Administration EAR without any application code.

### Build setup 1

1. Apply and configure the ATG runAssembler plugin for a Storefront EAR with Admin capabilities only with default commerce modules.
    ```
    plugins {
      id "net.hallatech.atg-runassembler" version("0.1")
    }
    
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DCS"]
        jboss = true
      }
    }
    ```

2. See the task listing:
    ```bash
    $ g tasks
    
    > Task :tasks
    
    ------------------------------------------------------------
    All tasks runnable from root project
    ------------------------------------------------------------
    
    ATG tasks
    ---------
    runAssembleAll - Executes ATG runAssembler for all assembly configurations 
    runAssembleStorefront - Executes ATG runAssembler for the storefront EAR
    ...

    ```
3. Run the storefront EAR assembly:
    ```bash
    $ g runAssemblerStorefront
    
    > Task :runAssembleStorefront
    runAssembler -jboss /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear -m DCS
    Warning: unable to determine Unix variant.  Assuming Solaris.
    The following installed Oracle Commerce components are being used to launch:
      ATGPlatform version 11.3 installed at /opt/ATG/ATG11.3
    
    Assembly started.
    Target application is /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear.
    Creating exploded ear file /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear.
    Initializing...
    Before make meta-inf file.
    Making meta-inf file /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear/META-INF
    Creating application.xml...
    Creating Ear file MANIFEST...
    Importing classes...
    Importing classes for module DafEar
    Importing classes for module DafEar.base
    Importing classes for module DCS
    Importing classes for module ContentMgmt
    Importing classes for module DSS
    Importing classes for module DPS
    Importing classes for module DAS-UI
    Importing classes for module DAS
    Ignoring classpath entry lib/servlet.jar from module DAS
    Ignoring classpath entry lib/jsp-api.jar from module DAS
    Importing J2EE module atg-bootstrap.war declared in DafEar.base
      Parsing /Users/hallatech/github/hallatech/atg-project-development/build/atg/ATGStorefront.ear/atg_bootstrap.war/WEB-INF/web.xml
    Importing J2EE module atg-bootstrap-ejb.jar declared in DafEar.base
    Assembly took 0.726 seconds.
    
    BUILD SUCCESSFUL in 1s
    1 actionable task: 1 executed
    ```

    The EAR is available at `build/atg/ATGStorefront.ear`
    
  








 


