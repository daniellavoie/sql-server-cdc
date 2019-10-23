-- Create the test database
CREATE DATABASE cdcdemo;
GO
USE cdcdemo;
create table customer (id varchar(255) not null, address_city varchar(255), address_number varchar(255), address_street varchar(255), country varchar(255), first_name varchar(255), last_name varchar(255), primary key (id));
GO