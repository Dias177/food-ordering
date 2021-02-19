INSERT INTO role (name) VALUES ('Admin');
INSERT INTO role (name) VALUES ('Customer');

-- Password is "Qwerty12!"
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number)
VALUES (1, 'admin@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'Admin', 'Adminov', '+77001234567');
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number, birthday)
VALUES (2, 'user1@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'User', 'Userov', '+77011234567', '2012-01-01');
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number, birthday)
VALUES (2, 'user2@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'User', 'Useruly', '+77021234567', '2015-05-08');
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number, birthday)
VALUES (2, 'user3@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'User', 'Userbay', '+77031234567', '2010-04-09');
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number, birthday)
VALUES (2, 'user4@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'Юзер', 'Юзербек', '+77041234567', '2003-10-23');
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number, birthday)
VALUES (2, 'user5@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'Юзер', 'Юзержан', '+77051234567', '1995-09-08');

INSERT INTO food_category (name) VALUES ('Japanese');
INSERT INTO food_category (name) VALUES ('European');
INSERT INTO food_category (name) VALUES ('Русская');
INSERT INTO food_category (name) VALUES ('Asian');
INSERT INTO food_category (name) VALUES ('Турецкая');
INSERT INTO food_category (name) VALUES ('Italian');

INSERT INTO food (food_category_id, name, description, price)
VALUES (1, 'Sushi', 'Some text', 3000);
INSERT INTO food (food_category_id, name, description, price)
VALUES (1, 'Ramen', 'Some text', 2000);
INSERT INTO food (food_category_id, name, description, price)
VALUES (2, 'Sausages', 'Some text', 1500);
INSERT INTO food (food_category_id, name, description, price)
VALUES (2, 'Paella', 'Some text', 2500);
INSERT INTO food (food_category_id, name, description, price)
VALUES (3, 'Борщ', 'Некоторый текст', 1000);
INSERT INTO food (food_category_id, name, description, price)
VALUES (3, 'Пельмени', 'Некоторый текст', 2000);
INSERT INTO food (food_category_id, name, description, price)
VALUES (4, 'Манты', 'Некоторый текст', 1500);
INSERT INTO food (food_category_id, name, description, price)
VALUES (4, 'Самса', 'Некоторый текст', 500);
INSERT INTO food (food_category_id, name, description, price)
VALUES (5, 'Донер', 'Некоторый текст',1000);
INSERT INTO food (food_category_id, name, description, price)
VALUES (5, 'Кебаб', 'Некоторый текст',1500);
INSERT INTO food (food_category_id, name, description, price)
VALUES (6, 'Pasta', 'Some text', 2000);
INSERT INTO food (food_category_id, name, description, price)
VALUES (6, 'Pizza', 'Some text', 2000);

INSERT INTO order_status (name) VALUES ('In processing');
INSERT INTO order_status (name) VALUES ('Preparing');
INSERT INTO order_status (name) VALUES ('Ready');
INSERT INTO order_status (name) VALUES ('Delivering');
INSERT INTO order_status (name) VALUES ('Finished');
INSERT INTO order_status (name) VALUES ('Cancelled');

INSERT INTO `order` (user_id, order_status_id, price, date)
VALUES (2, 1, 5000, NOW());
INSERT INTO `order` (user_id, order_status_id, price, date)
VALUES (3, 1, 6500, NOW());
INSERT INTO `order` (user_id, order_status_id, price, date)
VALUES (4, 1, 7000, NOW());
INSERT INTO `order` (user_id, order_status_id, price, date)
VALUES (4, 1, 13500, NOW());
INSERT INTO `order` (user_id, order_status_id, price, date)
VALUES (6, 1, 500, NOW());

INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (1, 11, 2000, 1);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (1, 7, 3000, 2);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (2, 3, 4500, 3);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (2, 2, 2000, 1);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (3, 6, 4000, 2);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (3, 10, 3000, 2);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (4, 4, 7500, 3);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (4, 1, 6000, 2);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (5, 8, 500, 1);