databaseChangeLog {
  changeSet(id:'core_atg_dcs_ddl',author:'hallatech',context:'ddl') {
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/claimable_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/commerce_user.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/dcs_mappers.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/inventory_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/order_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/contracts_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/organization_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/order_markers_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/user_giftlist_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/user_promotion_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/invoice_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/order_returns_ddl.sql')
    
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/priceLists_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/product_catalog_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/custom_catalog_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/promotion_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/location_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/dcs_content_mgmt_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/dcs_backing_maps_ddl.sql')
    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/commerce_site_ddl.sql')

    sqlFile(path:'Liquibase/install/core/ddl/atg/DCS/create_cms_procedures.sql',splitStatements:false)
  }
}
