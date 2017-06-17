create sequence users_sequence start with 100 increment by 1;
create table users (
    id int8 not null default nextval('users_sequence'),
    version int4,
    email varchar(255) not null unique,
    password varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    deleted boolean default false,
    primary key (id)
);

create sequence companies_sequence start with 100 increment by 1;
create table companies (
    id int8 not null default nextval('companies_sequence'),
    version int4,
    name varchar(255) not null,
    commission float4,
    created_at timestamp not null,
    updated_at timestamp not null,
    deleted boolean default false,
    user_id int8 not null,
    primary key (id)
);

alter table companies
    add constraint fk_companies_users
    foreign key (user_id)
    references users;
