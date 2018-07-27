


--     @version$Id: //product/ContentMgmt/version/11.3/src/sql/wcm_backing_maps_ddl.xml#2 $  
-- Tables for backing maps for item descriptors enables for dynamic properties

create table wcm_dyn_prop_map_str (
	id	varchar2(40)	not null,
	prop_name	varchar2(254)	not null,
	prop_value	varchar2(400)	null
,constraint wcm_dyn_prop_map_str_p primary key (id,prop_name));


create table wcm_dyn_prop_map_big_str (
	id	varchar2(40)	not null,
	prop_name	varchar2(254)	not null,
	prop_value	clob	null
,constraint wcm_dyn_prop_map_big_str_p primary key (id,prop_name));


create table wcm_dyn_prop_map_double (
	id	varchar2(40)	not null,
	prop_name	varchar2(254)	not null,
	prop_value	number(19,7)	null
,constraint wcm_dyn_prop_map_double_p primary key (id,prop_name));


create table wcm_dyn_prop_map_long (
	id	varchar2(40)	not null,
	prop_name	varchar2(254)	not null,
	prop_value	number(19)	null
,constraint wcm_dyn_prop_map_long_p primary key (id,prop_name));




