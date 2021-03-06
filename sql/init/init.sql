-- Postgres init script

DROP DATABASE IF EXISTS test;
DROP ROLE IF EXISTS test;

CREATE ROLE test WITH LOGIN;

CREATE DATABASE test
WITH OWNER test
ENCODING UTF8
LC_CTYPE 'en_US.utf8'
LC_COLLATE 'en_US.utf8';
