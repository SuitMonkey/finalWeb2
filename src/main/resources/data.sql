INSERT INTO users (email, first_name, last_name, password, role, shipping_address, city, country, status,rnc,department,id)
VALUES ('j@gmail.com', 'Jesus', 'Henriquez', '1234', 0, 'nowhere', 'nowhere', 'nowhere', 0,false,'ADMIN',123456789);
INSERT INTO history (history_id, user_email) VALUES (1, 'j@gmail.com');

INSERT INTO users (email, first_name, last_name, password, role, shipping_address, city, country, status,rnc,department)
VALUES ('fcaceres@avathartech.com', 'Francis', 'Caceres', '1234', 0, 'nowhere', 'nowhere', 'nowhere', 0,false,'STORAGE',987654321);
INSERT INTO history (history_id, user_email) VALUES (2, 'fcaceres@avathartech.com');


INSERT INTO items (product_id, product_description, product_in_stock, product_name, product_price, supplier)
VALUES (1, 'New Gucci fall line sunglasses', 45, 'Gucci Sunset Sunglasses', 190.99, 'Macys');

INSERT INTO items (product_id, product_description, product_in_stock, product_name, product_price, supplier)
VALUES (2, 'Women chique couture lace bra', 45, 'Fearless Brasier', 80.99, 'Victoria Secret');


INSERT INTO users (email, first_name, last_name, password, role, shipping_address, city, country, status,rnc,department)
VALUES ('eva@gmail.com', 'Eva', 'Soraya', '81dc9bdb52d04dc20036dbd8313ed055', 2, 'Villa Olga', 'Santiago', 'Dominican Republic', 0,false,'CONSUMER');
INSERT INTO history (history_id, user_email) VALUES (3, 'eva@gmail.com');
-- INSERT INTO history_shopping_cart (history_history_id, shopping_cart_product_id) VALUES (8,4);
-- INSERT INTO history_shopping_cart (history_history_id, shopping_cart_product_id) VALUES (8,5);

INSERT INTO users (email, first_name, last_name, password, role, shipping_address, city, country, status,rnc,department)
VALUES ('paulina@gmail.com', 'Paulina', 'La Mejor', '81dc9bdb52d04dc20036dbd8313ed055', 2, 'Rue 3', 'Paris', 'France', 0,false,'CONSUMER');
 INSERT INTO history (history_id, user_email) VALUES (4, 'paulina@gmail.com');
-- INSERT INTO history_shopping_cart (history_history_id, shopping_cart_product_id) VALUES (9,4);
-- INSERT INTO history_shopping_cart (history_history_id, shopping_cart_product_id) VALUES (9,5);
-- INSERT INTO history_browsing_history (history_history_id, browsing_history_product_id) VALUES (9,4);
-- INSERT INTO history_browsing_history (history_history_id, browsing_history_product_id) VALUES (9,5);




