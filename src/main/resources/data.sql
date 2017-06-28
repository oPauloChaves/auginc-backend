
/* EMPLOYEES */
INSERT INTO employees (id, version, email, first_name, last_name, title, deleted, password) VALUES (1, 0, 'admin@admin.com', 'Paulo', 'Chaves', 'Admin', false, '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi');
INSERT INTO employees (id, version, email, first_name, last_name, title, deleted, password) VALUES (2, 0, 'bob@auginc.com', 'Bob', 'Bob', 'Manager', false, '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');
INSERT INTO employees (id, version, email, first_name, last_name, title, deleted, password) VALUES (3, 0, 'ben@auginc.com', 'Ben', 'Ben', 'Manager', false, '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');

/* AUTHORITIES */
INSERT INTO authority (id, name, versao) VALUES (1, 'ROLE_USER', 0);
INSERT INTO authority (id, name, versao) VALUES (2, 'ROLE_MANAGER', 0);
INSERT INTO authority (id, name, versao) VALUES (3, 'ROLE_ADMIN', 0);

/* EMPLOYEES WITH AUTHORITIES */
INSERT INTO usuario_authority (employee_id, authority_id) VALUES (1, 3);
INSERT INTO usuario_authority (employee_id, authority_id) VALUES (2, 2);
INSERT INTO usuario_authority (employee_id, authority_id) VALUES (3, 2);

/* BRANDS */
INSERT INTO brands (id, version, name, commission, employee_id) VALUES (1, 0, 'Tupperware', '26.5', 1);
INSERT INTO brands (id, version, name, commission, employee_id) VALUES (2, 0, 'Natura', '30', 1);
INSERT INTO brands (id, version, name, commission, employee_id) VALUES (3, 0, 'Tupperware', '26.5', 2);
INSERT INTO brands (id, version, name, commission, employee_id) VALUES (4, 0, 'Tupperware', '26.5', 3);
INSERT INTO brands (id, version, name, commission, employee_id) VALUES (5, 0, 'MyClothes', '10', 3);
