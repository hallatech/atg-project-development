databaseChangeLog {
  changeSet(id:'core_atg_dss_ddl',author:'hallatech',context:'ddl') {
    sqlFile(path:'Liquibase/install/core/ddl/atg/DSS/business_process_rpt_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DSS/das_mappers.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DSS/dps_mappers.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DSS/dss_mappers.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DSS/markers_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DSS/profile_bp_markers_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DSS/scenario_ddl.sql')
  }
}