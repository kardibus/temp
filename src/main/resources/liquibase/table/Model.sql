--liquibase formatted sql
--changeset ShakirovMa:1

DROP TABLE IF EXISTS MODEL;

CREATE TABLE IF NOT EXISTS MODEL
(
    id   serial PRIMARY KEY,
    prog INT,
    curr INT,
    temp float,
    work BOOLEAN DEFAULT false
);

INSERT INTO model (id, prog, curr, temp, work)VALUES (1, 9, 1, 73.0, false);

--rollback drop table IF EXISTS MODEL;