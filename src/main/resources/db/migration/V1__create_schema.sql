create sequence employees_sequence start with 100 increment by 1;
create table employees (
    id int8 not null default nextval('employees_sequence'),
    email varchar(255) not null,
    password varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    phone_number varchar(255),
    title varchar(255),
    deleted boolean default false,
    enabled boolean not null default false,
    last_reset_password_date timestamp,
    token_reset_password varchar(255),
    version int4,
    created_at timestamp not null,
    modified_at timestamp,
    created_by varchar(255),
    modified_by varchar(255),
    primary key (id),
    constraint employees_email_uk unique (email)
);

create sequence brands_sequence start with 100 increment by 1;
create table brands (
    id int8 not null default nextval('brands_sequence'),
    version int4,
    name varchar(255) not null,
    commission numeric(5,2),
    deleted boolean default false,
    employee_id int8 not null,
    created_at timestamp not null,
    modified_at timestamp,
    created_by varchar(255),
    modified_by varchar(255),
    primary key (id),
    constraint brands_name_uk unique (name, employee_id)
);

create sequence customers_sequence start with 100 increment by 1;
create table customers (
    id int8 not null default nextval('customers_sequence'),
    version int4,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    phone_number varchar(255),
    cell_number varchar(255),
    note text,
    street_address varchar(255),
    street_address2 varchar(255),
    place_number varchar(255),
    zipcode varchar(255),
    city varchar(255),
    federal_state varchar(255),
    country varchar(255),
    latitude numeric(9,6),
    longitude numeric(9,6),
    employee_id int8 not null,
    created_at timestamp not null,
    modified_at timestamp,
    created_by varchar(255),
    modified_by varchar(255),
    deleted boolean default false,
    primary key (id),
    constraint customers_email_uk unique (email, employee_id)
);

create sequence authority_sequence start with 10 increment by 1;
create table authority (
    id int8 not null default nextval('authority_sequence'),
    version int4,
    name varchar(255) not null,
    primary key (id),
    constraint authority_name_uk unique (name)
);

create table employee_authority (
    employee_id int8 not null,
    authority_id int8 not null,
    constraint employee_authority_uk unique (employee_id, authority_id)
);

create sequence settings_sequence start with 10 increment by 1;
create table settings (
    id int8 not null default nextval('settings_sequence'),
    type varchar(255) not null,
    value varchar(255) not null,
    employee_id int8 not null,
    primary key (id),
    constraint settings_employee_uk unique (type, employee_id)
);

alter table brands
    add constraint fk_brands_employee_id
    foreign key (employee_id)
    references employees;

alter table customers
    add constraint fk_customers_employee_id
    foreign key (employee_id)
    references employees;

alter table employee_authority
    add constraint fk_employee_authority_employee_id
    foreign key (employee_id)
    references employees;

alter table employee_authority
    add constraint fk_employee_authority_authority_id
    foreign key (authority_id)
    references authority;

alter table settings
    add constraint fk_settings_employee_id
    foreign key (employee_id)
    references employees;
