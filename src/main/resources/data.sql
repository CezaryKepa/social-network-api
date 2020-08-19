insert into roles(name) VALUES ('ROLE_USER');
insert into roles(name) VALUES ('ROLE_ADMIN');
insert into customer(username) Values('user1');
insert into customer(username) Values('user2');
insert into customer(username) Values('user3');
insert into user_observers(observer_id, observed_id) values (2,3);
insert into user_observers(observer_id, observed_id) values (3,1);
insert into user_observers(observer_id, observed_id) values (2,1);
insert into user_observers(observer_id, observed_id) values (1,2);