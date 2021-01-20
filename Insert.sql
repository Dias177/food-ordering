INSERT INTO role (name) VALUES ('Admin');
INSERT INTO role (name) VALUES ('Customer');

-- Password is Qwerty12!
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number)
VALUES (1, 'admin@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'Admin', 'Adminov', '+77001234567');
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number)
VALUES (2, 'user1@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'User', 'Userov', '+77011234567');
INSERT INTO user (role_id, email, password, first_name, last_name, phone_number)
VALUES (2, 'user2@test.com', '96dee1bb26cf7cd9167a4882dc494b8c', 'User', 'Userov', '+77021234567');

INSERT INTO food_category (name) VALUES ('Japanese');
INSERT INTO food_category (name) VALUES ('European');
INSERT INTO food_category (name) VALUES ('Russian');
INSERT INTO food_category (name) VALUES ('Asian');
INSERT INTO food_category (name) VALUES ('Turkish');
INSERT INTO food_category (name) VALUES ('Italian');

INSERT INTO food (food_category_id, name, price)
VALUES (1, 'Sushi', 3000);
INSERT INTO food (food_category_id, name, price)
VALUES (5, 'Doner', 1000);
INSERT INTO food (food_category_id, name, price)
VALUES (6, 'Pasta', 2000);
INSERT INTO food (food_category_id, name, price)
VALUES (3, 'Borsh', 1000);

INSERT INTO order_status (name) VALUES ('In processing');
INSERT INTO order_status (name) VALUES ('Preparing');
INSERT INTO order_status (name) VALUES ('Ready');
INSERT INTO order_status (name) VALUES ('Delivering');
INSERT INTO order_status (name) VALUES ('Finished');
INSERT INTO order_status (name) VALUES ('Cancelled');

INSERT INTO order (user_id, order_status_id, price, date)
VALUES (2, 1, 3000, NOW());
INSERT INTO order (user_id, order_status_id, price, date)
VALUES (3, 1, 7000, NOW());

INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (1, 2, 2000, 2);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (1, 4, 1000, 1);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (2, 1, 3000, 1);
INSERT INTO order_detail (order_id, food_id, price, quantity)
VALUES (2, 3, 4000, 2);