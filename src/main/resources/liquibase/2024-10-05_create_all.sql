--liquibase formatted sql
--changeset shakirov-ma:2024-10-05

create table data
(
    id         uuid not null,
    created_at timestamp(6),
    temp       float(53),
    primary key (id)
);

create table program
(
    id         uuid         not null,
    created_at timestamp(6),
    active     boolean,
    name       varchar(255) not null,
    pause      boolean,
    work       boolean,
    program_id uuid,
    primary key (id)
);

create table step
(
    id              uuid           not null,
    created_at      timestamp(6),
    date_end        timestamp(6),
    date_start      timestamp(6),
    done            boolean,
    step            integer unique not null,
    temp            float(53),
    time            integer,
    work            boolean,
    user_brewery_id uuid,
    primary key (id)
);

create table user_brewery
(
    id         uuid         not null,
    created_at timestamp(6),
    email      varchar(255),
    name       varchar(255) not null,
    primary key (id)
);

alter table if exists program
    add constraint fk_program foreign key (program_id) references user_brewery;
alter table if exists step
    add constraint fk_step foreign key (user_brewery_id) references program;

alter table if exists step add constraint uniqueConstraint_id_step unique (id, step)