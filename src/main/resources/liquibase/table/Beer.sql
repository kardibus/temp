--liquibase formatted sql
--changeset ShakirovMa:1

DROP TABLE IF EXISTS BEER;

CREATE TABLE IF NOT EXISTS BEER
(
    id   serial PRIMARY KEY,
    temp float
);

INSERT INTO BEER (id, temp)VALUES (1,73.0);

--rollback drop table IF EXISTS BEER;