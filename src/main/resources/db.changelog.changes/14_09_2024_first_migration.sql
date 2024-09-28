create table pizzas(
    id serial primary key,
    title varchar(50),
    price BIGINT
);
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price BIGINT
);


CREATE TABLE pizza_products (
    pizza_id BIGINT REFERENCES pizzas(id),
    product_id BIGINT REFERENCES products(id),
    PRIMARY KEY (pizza_id, product_id)
);

INSERT INTO products (name, price) VALUES ('Tomato sauce', 50);
INSERT INTO products (name, price) VALUES ('Mozzarella', 100);
INSERT INTO products (name, price) VALUES ('Basil', 30);
INSERT INTO products (name, price) VALUES ('Pepperoni', 120);
INSERT INTO products (name, price) VALUES ('BBQ Sauce', 70);
INSERT INTO products (name, price) VALUES ('Chicken', 150);
INSERT INTO products (name, price) VALUES ('Onions', 40);
INSERT INTO products (name, price) VALUES ('Ham', 110);
INSERT INTO products (name, price) VALUES ('Bell peppers', 60);
INSERT INTO products (name, price) VALUES ('Mushrooms', 80);
INSERT INTO products (name, price) VALUES ('Olives', 90);
INSERT INTO products (name, price) VALUES ('Gorgonzola', 130);
INSERT INTO products (name, price) VALUES ('Parmesan', 140);
INSERT INTO products (name, price) VALUES ('Sausage', 120);
INSERT INTO products (name, price) VALUES ('Bacon', 150);
INSERT INTO products (name, price) VALUES ('Buffalo sauce', 75);
INSERT INTO products (name, price) VALUES ('Shrimp', 200);
INSERT INTO products (name, price) VALUES ('Squid', 180);
INSERT INTO products (name, price) VALUES ('Garlic', 30);
INSERT INTO products (name, price) VALUES ('Blue cheese', 160);
INSERT INTO products (name, price) VALUES ('Spinach', 50);
INSERT INTO products (name, price) VALUES ('Feta cheese', 110);
INSERT INTO products (name, price) VALUES ('Capers', 90);
INSERT INTO products (name, price) VALUES ('Tuna', 170);
INSERT INTO products (name, price) VALUES ('Cream sauce', 60);
INSERT INTO products (name, price) VALUES ('Artichokes', 100);
INSERT INTO products (name, price) VALUES ('Spicy beef', 130);
INSERT INTO products (name, price) VALUES ('Jalapeños', 50);
INSERT INTO products (name, price) VALUES ('Prosciutto', 190);
INSERT INTO products (name, price) VALUES ('Truffle oil', 300);
INSERT INTO products (name, price) VALUES ('Arugula', 40);
INSERT INTO products (name, price) VALUES ('Bell peppers', 60);
INSERT INTO products (name, price) VALUES ('Fried eggplant', 90);
INSERT INTO products (name, price) VALUES ('Cheddar', 110);
INSERT INTO products (name, price) VALUES ('Ground beef', 140);
INSERT INTO products (name, price) VALUES ('Pesto sauce', 80);
INSERT INTO products (name, price) VALUES ('Chicken', 150);
INSERT INTO products (name, price) VALUES ('Tomatoes', 50);



INSERT INTO pizzas (title, price)
VALUES ('Margherita',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 2, 3)));

INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Margherita'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Margherita'), 2),
       ((SELECT id FROM pizzas WHERE title = 'Margherita'), 3);



INSERT INTO pizzas (title, price)
VALUES ('Pepperoni',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 2, 4)));

INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Pepperoni'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Pepperoni'), 2),
       ((SELECT id FROM pizzas WHERE title = 'Pepperoni'), 4);



INSERT INTO pizzas (title, price)
VALUES ('BBQ Chicken',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (5, 6, 7, 2)));


INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'BBQ Chicken'), 5),
       ((SELECT id FROM pizzas WHERE title = 'BBQ Chicken'), 6),
       ((SELECT id FROM pizzas WHERE title = 'BBQ Chicken'), 7),
       ((SELECT id FROM pizzas WHERE title = 'BBQ Chicken'), 2);


INSERT INTO pizzas (title, price)
VALUES ('Hawaiian',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 8, 9, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Hawaiian'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Hawaiian'), 8),
       ((SELECT id FROM pizzas WHERE title = 'Hawaiian'), 9),
       ((SELECT id FROM pizzas WHERE title = 'Hawaiian'), 2);


INSERT INTO pizzas (title, price)
VALUES ('Veggie',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 10, 7, 11, 12, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Veggie'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Veggie'), 10),
       ((SELECT id FROM pizzas WHERE title = 'Veggie'), 7),
       ((SELECT id FROM pizzas WHERE title = 'Veggie'), 11),
       ((SELECT id FROM pizzas WHERE title = 'Veggie'), 12),
       ((SELECT id FROM pizzas WHERE title = 'Veggie'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Four Cheese',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 2, 13, 14, 15)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Four Cheese'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Four Cheese'), 2),
       ((SELECT id FROM pizzas WHERE title = 'Four Cheese'), 13),
       ((SELECT id FROM pizzas WHERE title = 'Four Cheese'), 14),
       ((SELECT id FROM pizzas WHERE title = 'Four Cheese'), 15);

INSERT INTO pizzas (title, price)
VALUES ('Meat Lover''s',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 4, 16, 8, 17, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Meat Lover''s'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Meat Lover''s'), 4),
       ((SELECT id FROM pizzas WHERE title = 'Meat Lover''s'), 16),
       ((SELECT id FROM pizzas WHERE title = 'Meat Lover''s'), 8),
       ((SELECT id FROM pizzas WHERE title = 'Meat Lover''s'), 17),
       ((SELECT id FROM pizzas WHERE title = 'Meat Lover''s'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Seafood',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 18, 19, 20, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Seafood'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Seafood'), 18),
       ((SELECT id FROM pizzas WHERE title = 'Seafood'), 19),
       ((SELECT id FROM pizzas WHERE title = 'Seafood'), 20),
       ((SELECT id FROM pizzas WHERE title = 'Seafood'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Buffalo Chicken',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (21, 6, 22, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Buffalo Chicken'), 21),
       ((SELECT id FROM pizzas WHERE title = 'Buffalo Chicken'), 6),
       ((SELECT id FROM pizzas WHERE title = 'Buffalo Chicken'), 22),
       ((SELECT id FROM pizzas WHERE title = 'Buffalo Chicken'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Spinach & Feta',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 23, 24, 20, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Spinach & Feta'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Spinach & Feta'), 23),
       ((SELECT id FROM pizzas WHERE title = 'Spinach & Feta'), 24),
       ((SELECT id FROM pizzas WHERE title = 'Spinach & Feta'), 20),
       ((SELECT id FROM pizzas WHERE title = 'Spinach & Feta'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Tuna',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 25, 7, 26, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Tuna'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Tuna'), 25),
       ((SELECT id FROM pizzas WHERE title = 'Tuna'), 7),
       ((SELECT id FROM pizzas WHERE title = 'Tuna'), 26),
       ((SELECT id FROM pizzas WHERE title = 'Tuna'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Mushroom Delight',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (27, 11, 20, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Mushroom Delight'), 27),
       ((SELECT id FROM pizzas WHERE title = 'Mushroom Delight'), 11),
       ((SELECT id FROM pizzas WHERE title = 'Mushroom Delight'), 20),
       ((SELECT id FROM pizzas WHERE title = 'Mushroom Delight'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Capricciosa',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 8, 11, 28, 12, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Capricciosa'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Capricciosa'), 8),
       ((SELECT id FROM pizzas WHERE title = 'Capricciosa'), 11),
       ((SELECT id FROM pizzas WHERE title = 'Capricciosa'), 28),
       ((SELECT id FROM pizzas WHERE title = 'Capricciosa'), 12),
       ((SELECT id FROM pizzas WHERE title = 'Capricciosa'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Mexican',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 29, 30, 7, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Mexican'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Mexican'), 29),
       ((SELECT id FROM pizzas WHERE title = 'Mexican'), 30),
       ((SELECT id FROM pizzas WHERE title = 'Mexican'), 7),
       ((SELECT id FROM pizzas WHERE title = 'Mexican'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Truffle & Prosciutto',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (31, 32, 33, 14, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Truffle & Prosciutto'), 31),
       ((SELECT id FROM pizzas WHERE title = 'Truffle & Prosciutto'), 32),
       ((SELECT id FROM pizzas WHERE title = 'Truffle & Prosciutto'), 33),
       ((SELECT id FROM pizzas WHERE title = 'Truffle & Prosciutto'), 14),
       ((SELECT id FROM pizzas WHERE title = 'Truffle & Prosciutto'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Sausage & Peppers',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 16, 10, 7, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Sausage & Peppers'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Sausage & Peppers'), 16),
       ((SELECT id FROM pizzas WHERE title = 'Sausage & Peppers'), 10),
       ((SELECT id FROM pizzas WHERE title = 'Sausage & Peppers'), 7),
       ((SELECT id FROM pizzas WHERE title = 'Sausage & Peppers'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Eggplant Parm',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 34, 13, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Eggplant Parm'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Eggplant Parm'), 34),
       ((SELECT id FROM pizzas WHERE title = 'Eggplant Parm'), 13),
       ((SELECT id FROM pizzas WHERE title = 'Eggplant Parm'), 2);

INSERT INTO pizzas (title, price)
VALUES ('Greek',
        (SELECT SUM(p.price) * 1.1
         FROM products p
         WHERE p.id IN (1, 24, 12, 35, 7, 2)));
INSERT INTO pizza_products (pizza_id, product_id)
VALUES ((SELECT id FROM pizzas WHERE title = 'Greek'), 1),
       ((SELECT id FROM pizzas WHERE title = 'Greek'), 24),
       ((SELECT id FROM pizzas WHERE title = 'Greek'), 12),
       ((SELECT id FROM pizzas WHERE title = 'Greek'), 35),
       ((SELECT id FROM pizzas WHERE title = 'Greek'), 7),
       ((SELECT id FROM pizzas WHERE title = 'Greek'), 2);



