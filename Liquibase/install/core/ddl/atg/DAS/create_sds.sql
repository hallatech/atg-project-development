


--  @version $Id: //product/DAS/version/11.3/templates/DAS/sql/create_sds.xml#2 $$Change: 1393648 $

create table das_sds (
	sds_name	varchar2(50)	not null,
	curr_ds_name	varchar2(50)	null,
	dynamo_server	varchar2(80)	null,
	last_modified	date	null
,constraint das_sds_p primary key (sds_name));




