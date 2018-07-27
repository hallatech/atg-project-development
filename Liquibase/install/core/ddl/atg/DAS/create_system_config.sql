


--  @version $Id: //product/DAS/version/11.3/templates/DAS/sql/create_system_config.xml#2 $$Change: 1393648 $

create table das_sys_config (
	parameter_key	varchar2(50)	not null,
	parameter_value	varchar2(255)	not null
,constraint das_sys_config_p primary key (parameter_key));




