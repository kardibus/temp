--liquibase formatted sql
--changeset ShakirovMa:1

DROP TABLE IF EXISTS BEER;

CREATE SEQUENCE IF NOT EXISTS public.testincrement_sequence_beer INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE IF NOT EXISTS BEER
(
    id   integer PRIMARY KEY NOT NULL DEFAULT nextval('testincrement_sequence_beer'::regclass),
    temp float
);

--rollback drop table IF EXISTS BEER;