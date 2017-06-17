create table Store (
    id int8 not null,
    version int4,
    name varchar(255) not null,
    email varchar(255) not null unique,
    status varchar(30) not null,
    primary key (id)
);

create sequence hibernate_sequence;

