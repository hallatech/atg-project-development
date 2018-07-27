


--     @version$Id: //product/DAS/version/11.3/templates/DAS/sql/dynamic_metadata_ddl.xml#2 $  
-- Tables for Dynamic Repository Metadata storage

create table das_gsa_dyn_type (
	id	varchar2(40)	not null,
	type_name	varchar2(254)	null,
	item_descriptor	varchar2(254)	null,
	root_item_descriptor	varchar2(254)	null,
	repository	varchar2(254)	null,
	sub_type_value	varchar2(254)	null,
	sub_type_num	number(10)	null,
	removed	number(1)	null
,constraint das_gsa_dyn_type_p primary key (id));


create table das_gsa_dyn_prop (
	id	varchar2(40)	not null,
	property_name	varchar2(254)	not null,
	item_descriptor	varchar2(254)	null,
	repository	varchar2(254)	null,
	data_type	varchar2(254)	null,
	backing_map	varchar2(254)	null,
	localized_prop	number(1)	null,
	class_name	varchar2(254)	null,
	removed	number(1)	null
,constraint das_gsa_dyn_prop_p primary key (id));


create table das_gsa_dyn_prop_elocale (
	id	varchar2(40)	not null,
	localized_key	varchar2(165)	not null,
	localized_value	varchar2(265)	null
,constraint das_gsa_dyn_prop_elocale_p primary key (id,localized_key));


create table das_gsa_dyn_prop_enum (
	id	varchar2(40)	not null,
	enumeration_values	varchar2(254)	not null,
	enumeration_value_num	number(10)	not null
,constraint das_gsa_dyn_prop_enum_p primary key (id,enumeration_values));


create table das_gsa_dyn_prop_eorder (
	id	varchar2(40)	not null,
	seq_num	number(10)	not null,
	enumeration_order	varchar2(254)	not null
,constraint das_gsa_dyn_prop_eorder_p primary key (id,seq_num));


create table das_gsa_dyn_attr (
	id	varchar2(300)	not null,
	attribute_name	varchar2(254)	not null,
	property_name	varchar2(254)	null,
	item_descriptor	varchar2(254)	null,
	repository	varchar2(254)	null,
	locale	varchar2(254)	null,
	is_dynamic_property	number(1)	null,
	removed	number(1)	null,
	data_type	varchar2(254)	null,
	attr_value	varchar2(254)	null,
	original_value	varchar2(254)	null
,constraint das_gsa_dynamic_attr_p primary key (id));


create table das_gsa_dyn_attr_bigstr (
	id	varchar2(300)	not null,
	big_str_value	clob	not null
,constraint das_gsa_dyn_attr_bigstr_p primary key (id));




