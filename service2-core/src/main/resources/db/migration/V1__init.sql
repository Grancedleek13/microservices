create extension if not exists "pgcrypto";

create table if not exists users(
    id uuid primary key default gen_random_uuid(),
    external_id varchar(255) not null unique,
    service_type varchar(16) not null,
    last_status varchar(16) not null,
    last_seen_at timestamp not null,
    created_at timestamp not null
);

create table if not exists messages(
    id uuid primary key default gen_random_uuid(),
    user_id uuid not null references users(id) on delete cascade,
    text text not null,
    received_at timestamp not null
);

create table if not exists activities(
    id uuid primary key default gen_random_uuid(),
    user_id uuid not null references users(id) on delete cascade,
    status varchar(16) not null,
    occurred_at timestamp not null
);

create table if not exists command_logs(
    id uuid primary key default gen_random_uuid(),
    source_service_id varchar(255) not null,
    action varchar(64) not null,
    payload text not null,
    success boolean not null,
    created_at timestamp not null
);
