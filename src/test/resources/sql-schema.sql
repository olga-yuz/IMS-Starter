drop schema ims1;
CREATE DATABASE IF NOT EXISTS ims1;
use ims1;

create table if not exists customers
(
cust_id int unique not null auto_increment,
cust_name varchar(100) not null,
primary key(cust_id)
);
create table if not exists items
(
item_id int unique not null auto_increment,
price double(7,2) not null,
item_name varchar(70) not null,
primary key(item_id)
);
create table if not exists orders
(
order_id int unique not null auto_increment,
fk_cust_id int not null,
primary key(order_id),
foreign key(fk_cust_id) references customers(cust_id)
);
create table if not exists orders_items
(
orders_items_id int unique not null auto_increment,
quantity int not null,
fk_order_id int not null,
fk_item_id int not null,
primary key(orders_items_id),
foreign key(fk_order_id) references orders(order_id),
foreign key(fk_item_id) references items(item_id)
);
