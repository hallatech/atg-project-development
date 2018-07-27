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