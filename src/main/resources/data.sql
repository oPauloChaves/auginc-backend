
/* USERS */
INSERT INTO users (id, version, created_at, updated_at, email, first_name, last_name, password, deleted) VALUES (1, 0, '2017-06-01 00:00:00', '2017-06-01 00:00:00', 'paulochavesbr@gmail.com', 'Paulo', 'Chaves', '123456', false);
INSERT INTO users (id, version, created_at, updated_at, email, first_name, last_name, password, deleted) VALUES (2, 0, '2017-06-01 00:00:00', '2017-06-01 00:00:00', 'john@test.com', 'John', 'Peace', '123456', false);

/* COMPANIES */
INSERT INTO companies (id, version, created_at, updated_at, name, commission, user_id, deleted) VALUES (1, 0, '2017-06-01 00:00:00', '2017-06-01 00:00:00', 'Tupplasticos', null, 1, false);
INSERT INTO companies (id, version, created_at, updated_at, name, commission, user_id, deleted) VALUES (2, 0, '2017-06-01 00:00:00', '2017-06-01 00:00:00', 'Avontade', null, 1, false);
INSERT INTO companies (id, version, created_at, updated_at, name, commission, user_id, deleted) VALUES (3, 0, '2017-06-01 00:00:00', '2017-06-01 00:00:00', 'Tupplasticos', null, 2, false);
