drop table if exists user;
create table user (id int AUTO_INCREMENT PRIMARY KEY, username VARCHAR(45));
INSERT INTO user (id, username) VALUES (1, 'user1'), (2, 'user2'), (3, 'user3');
