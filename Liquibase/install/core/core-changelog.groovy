databaseChangeLog {
  include(file:'Liquibase/install/core/ddl/atg/install.groovy')
  include(file:'Liquibase/install/core/procs/atg/install.groovy')
}
