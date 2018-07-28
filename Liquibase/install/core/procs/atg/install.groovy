databaseChangeLog {
  include(file:'Liquibase/install/core/procs/atg/das/install.groovy')

  changeSet(id:'core_atg_procs',author:'hallatech',context:'ddl') {
    tagDatabase(tag:'core_atg_procs')
  }
}
