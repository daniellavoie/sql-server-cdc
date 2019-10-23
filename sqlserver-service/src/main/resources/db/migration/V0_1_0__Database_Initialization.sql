create table customer (id bigint identity not null, address_city varchar(255), address_number varchar(255), address_street varchar(255), country varchar(255), first_name varchar(255), last_name varchar(255), primary key (id));
GO
EXEC sys.sp_cdc_enable_table @source_schema = 'dbo', @source_name = 'customer', @role_name = NULL, @supports_net_changes = 0;
GO