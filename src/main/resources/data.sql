
/* EMPLOYEES */
INSERT INTO employees (id, version, email, first_name, last_name, title, deleted, enabled, password, created_at) VALUES (1, 0, 'admin@user.com', 'Paulo', 'Chaves', 'Admin', false, true, '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', '2017-06-01 09:50:00');
INSERT INTO employees (id, version, email, first_name, last_name, title, deleted, enabled, password, created_at) VALUES (2, 0, 'bob@user.com', 'Bob', 'Bob', 'Manager', false, true, '$2a$10$NeU207fN9Td3zT0DC74IGO6E7lITQQiqzBTJJeMFDmiqxkAuH9ZIK', '2017-06-01 09:50:00');
INSERT INTO employees (id, version, email, first_name, last_name, title, deleted, enabled, password, created_at) VALUES (3, 0, 'ben@user.com', 'Ben', 'Ben', 'Manager', false, true, '$2a$10$NeU207fN9Td3zT0DC74IGO6E7lITQQiqzBTJJeMFDmiqxkAuH9ZIK', '2017-06-01 09:50:00');

/* AUTHORITIES */
INSERT INTO authority (id, name, version) VALUES (1, 'ROLE_USER', 0);
INSERT INTO authority (id, name, version) VALUES (2, 'ROLE_MANAGER', 0);
INSERT INTO authority (id, name, version) VALUES (3, 'ROLE_ADMIN', 0);

/* EMPLOYEES WITH AUTHORITIES */
INSERT INTO employee_authority (employee_id, authority_id) VALUES (1, 3);
INSERT INTO employee_authority (employee_id, authority_id) VALUES (2, 2);
INSERT INTO employee_authority (employee_id, authority_id) VALUES (3, 2);

/* BRANDS */
INSERT INTO brands (id, version, name, commission, employee_id, created_at) VALUES (1, 0, 'Tupperware', '26.5', 1, '2017-06-01 09:50:00');
INSERT INTO brands (id, version, name, commission, employee_id, created_at) VALUES (2, 0, 'Natura', '30', 1, '2017-06-01 09:50:00');
INSERT INTO brands (id, version, name, commission, employee_id, created_at) VALUES (3, 0, 'Tupperware', '26.5', 2, '2017-06-01 09:50:00');
INSERT INTO brands (id, version, name, commission, employee_id, created_at) VALUES (4, 0, 'Tupperware', '26.5', 3, '2017-06-01 09:50:00');
INSERT INTO brands (id, version, name, commission, employee_id, created_at) VALUES (5, 0, 'MyClothes', '10', 3, '2017-06-01 09:50:00');
