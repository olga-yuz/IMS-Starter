
INSERT INTO ims1.customers (cust_name) VALUES ('Jean-Luc Picard');
INSERT INTO ims1.customers (cust_name) VALUES ('Worf');
INSERT INTO ims1.items (price, item_name) VALUES ('3.50', 'Tea, Earl Grey, Hot');
INSERT INTO ims1.items (price, item_name) VALUES ('4.99', 'Prune Juice');
INSERT INTO ims1.orders (fk_cust_id) VALUES ('1');
INSERT INTO ims1.orders_items (quantity, fk_order_id, fk_item_id) VALUES ('3', '1', '1');