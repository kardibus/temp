--liquibase formatted sql
--changeset ShakirovMa:1

DROP TABLE IF EXISTS BEER;

CREATE TABLE IF NOT EXISTS BEER
(
    id   serial PRIMARY KEY,
    temp float
);

--rollback drop table IF EXISTS BEER;