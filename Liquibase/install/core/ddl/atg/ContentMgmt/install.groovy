databaseChangeLog {
  changeSet(id:'core_atg_das_ddl',author:'hallatech',context:'ddl') {
    sqlFile(path:'Liquibase/install/core/ddl/atg/ContentMgmt/content_mgmt_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/ContentMgmt/wcm_backing_maps_ddl.sql')
  }
}
