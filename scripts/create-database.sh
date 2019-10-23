#!/bin/bash

cat scripts/create-database.sql | docker exec -i sql-server-cdc-db bash -c '/opt/mssql-tools/bin/sqlcmd -U sa -P Password!'
cat scripts/create-replicated-database.sql | docker exec -i sql-server-cdc-replicated-db bash -c '/opt/mssql-tools/bin/sqlcmd -U sa -P Password!'