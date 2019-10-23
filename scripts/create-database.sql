-- Create the test database
CREATE DATABASE cdcdemo;
GO
USE cdcdemo;
EXEC sys.sp_cdc_enable_db;
GO