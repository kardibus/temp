--liquibase formatted sql
--changeset shakirov-ma:2024-10-05

create table data
(
    id   uuid not null,
    date timestamp(6),
    temp float(53),
    primary key (id)
);

create table data_work
(
    id           uuid not null,
    current_step bigint,
    program      bigint,
    temp         float(53),
    work         boolean,
    primary key (id)
);

create table program
(
    id    uuid         not null,
    name  varchar(255) not null,
    pause boolean,
    work  boolean,
    primary key (id)
);

create table step
(
    id         uuid         not null,
    date_end   timestamp(6),
    date_start timestamp(6),
    done       boolean,
    step       integer      not null,
    temp       bigint,
    time       integer,
    work       boolean,
    program_id uuid,
    primary key (id)
);

alter table if exists step
    add constraint fk_step foreign key (step_id) references program;