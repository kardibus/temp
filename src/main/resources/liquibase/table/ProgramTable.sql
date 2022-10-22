--liquibase formatted sql
--changeset ShakirovMa:1

DROP TABLE IF EXISTS PROGRAM;

CREATE SEQUENCE IF NOT EXISTS public.testincrement_sequence_program INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE IF NOT EXISTS PROGRAM
(
    id   integer PRIMARY KEY NOT NULL DEFAULT nextval('testincrement_sequence_program'::regclass),
    name varchar(255),
    work boolean                      default false,
    pause boolean                     default true
);

--rollback drop table IF EXISTS PROGRAM;

DROP TABLE IF EXISTS STEP;

CREATE SEQUENCE IF NOT EXISTS public.testincrement_sequence_step INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE IF NOT EXISTS STEP
(
    id integer PRIMARY KEY NOT NULL DEFAULT nextval('testincrement_sequence_step'::regclass),
    step integer,
    time integer,
    fromDate timestamp,
    toDate timestamp,
    prog_id integer,
    FOREIGN KEY
(prog_id) REFERENCES PROGRAM(Id));

--rollback drop table IF EXISTS STEP;