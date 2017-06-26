create sequence employees_sequence start with 100 increment by 1;
create table employees (
    id int8 not null default default nextval('employees_sequence'),
    email varchar(255) not null unique
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    phone_number varchar(255),
    title varchar(255),
    deleted boolean default false,
    version int4,
    primary key (id)
);

create sequence brands_sequence start with 100 increment by 1;
create table brands (
    id int8 not null default nextval('brands_sequence'),
    version int4,
    name varchar(255) not null,
    commission float4,
    deleted boolean default false,
    employees_id int8 not null,
    primary key (id)
);

alter table brands
    add constraint fk_brands_employees
    foreign key (employees_id)
    references employees;
