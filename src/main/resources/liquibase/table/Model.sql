--liquibase formatted sql
--changeset ShakirovMa:1

DROP TABLE IF EXISTS MODEL;

CREATE SEQUENCE IF NOT EXISTS public.testincrement_sequence_model INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE IF NOT EXISTS MODEL
(
    id   integer PRIMARY KEY NOT NULL DEFAULT nextval('testincrement_sequence_model'::regclass),
    prog integer,
    curr integer,
    temp float,
    work BOOLEAN DEFAULT false
);

INSERT INTO model (id, prog, curr, temp, work)VALUES (0, 9, 1, 73.0, false);

--rollback drop table IF EXISTS MODEL;