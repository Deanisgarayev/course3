--liquibase formatted sql
--changeset dgaraev:createTable
create table notification_tasks(id bigserial primary key, notification text, date_and_time timestamp);
