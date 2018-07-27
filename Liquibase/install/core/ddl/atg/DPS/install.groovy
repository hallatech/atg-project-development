databaseChangeLog {
  changeSet(id:'core_atg_dps_ddl',author:'hallatech',context:'ddl') {
    sqlFile(path:'Liquibase/install/core/ddl/atg/DPS/user_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DPS/logging_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DPS/logging_init.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DPS/personalization_ddl.sql')
  }
}
