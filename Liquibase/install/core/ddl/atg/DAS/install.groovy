databaseChangeLog {
  changeSet(id:'core_atg_das_ddl',author:'hallatech',context:'ddl') {
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/id_generator.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/dms_limbo_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/cluster_name_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/create_sql_jms_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/create_gsa_subscribers_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/create_sds.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/integration_data_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/nucleus_security_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/media_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/deployment_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/sitemap_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/purge_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/usage_tracking_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/rest_resource_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/seo_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/site_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/create_staff_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/create_system_config.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/dynamic_metadata_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/das/backing_maps_ddl.sql')
  }
}
