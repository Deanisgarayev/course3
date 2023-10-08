--liquibase formatted sql
--changeset dgaraev:createFirstTable
create table chat(id serial primary key, notification text, date_and_time timestamp);