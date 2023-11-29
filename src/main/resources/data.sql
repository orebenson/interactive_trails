insert into roles_table (name)
values
    ('ADMIN'),
    ('USER');

insert into trail_table (name, location, description)
values ('Cardiff Castle to Queen Street', 'Cardiff', 'A serene town along the shore, blending cobblestone streets with beachside cafes and historic cottages.');
insert into trail_table (name, location, description)
values ('Newport Castle to the Transporter Bridge', 'Newport','Newport, a historic town, showcases colonial charm with cobbled streets and well-preserved architecture. Situated along the river, it reflects a blend of heritage and modernity, making it a captivating destination in Wales.');
insert into trail_table (name, location, description)
values ('Swansea Mumbles to Gower', 'Swansea','Nestled along the scenic coastline, Swansea is a coastal haven known for its quaint beauty. With sandy beaches, a bustling marina, and a rich maritime history, the town offers a serene escape by the sea.');

insert into roles_table (role_id, name)
values (1, 'ADMIN'), (2, 'USER');

-- add default admin account
insert into user_table (username, password, enabled)
values ('admin', '$2a$12$zKMHlbOC7UXOLCdq5ZibC.ANCzcxgpoGbode97Dc2Fi1zakG2fP6O', true);

insert into users_roles (username, role_id)
values ('admin', 1);

-- add a default user, not currently working as bcrypt encoded password needed
# insert into user_table (username, password, enabled)
# values ('test', '$2a$12$NLq52ZFttP9BUQ78oKxjwuX/yED0HFV0eQFDvWzbwEgMhBqNk7rSG', true);
#
# insert into users_roles (username, role_id)
# values ('user', 2);

