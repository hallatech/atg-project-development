


--  @version $Id: //product/DAS/version/11.3/templates/DAS/sql/rest_resource_ddl.xml#2 $$Change: 1393648 $
-- This file contains create table statements, which will configure your database for use with the REST schema.

create table rest_resource_ver (
	res_uri	varchar2(254)	not null,
	version	integer	not null
,constraint rest_resource_ver_p primary key (res_uri));




