insert into roles_table (role_id, name)
values
    (1, 'ADMIN'),
    (2, 'USER');

-- add default admin account
insert into user_table (username, password, enabled)
values ('admin', '$2a$12$zKMHlbOC7UXOLCdq5ZibC.ANCzcxgpoGbode97Dc2Fi1zakG2fP6O', true);

insert into users_roles (username, role_id)
values ('admin', 1);

--Inserting Medal Types into medal_types
INSERT INTO medal_types (medal_name) VALUES ('BRONZE');
INSERT INTO medal_types (medal_name) VALUES ('SILVER');
INSERT INTO medal_types (medal_name) VALUES ('GOLD');

--Inserting default medals into medal_users table for users based on number of checkpoints collected
INSERT INTO medals_users (medal_name, username) VALUES ('BRONZE', 'admin');
INSERT INTO medals_users (medal_name, username) VALUES ('SILVER', 'admin');
INSERT INTO medals_users (medal_name, username) VALUES ('GOLD', 'admin');

