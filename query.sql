create database stock;
use stock;

# Populate tables

insert into category (id, active, name) values (1, 1, 'Vestuário');
insert into category (id, active, name) values (2, 1, 'Computadores');
insert into category (id, active, name) values (3, 1, 'Eletrônicos');
insert into category (id, active, name) values (4, 1, 'Cama, mesa e banho');
insert into category (id, active, name) values (5, 1, 'Eletrodomésticos');

insert into product (id, active, name) values (1, 1, 'Notebook ASUS 15"');
insert into product (id, active, name) values (2, 1, 'Tênis Nike Masculino Branco');
insert into product (id, active, name) values (3, 1, 'Monitor Samsung 24');
insert into product (id, active, name) values (4, 1, 'Toalha de banho Buddemeyer');
insert into product (id, active, name) values (5, 1, 'AirFryer Arno');

insert into product_category (category_id, product_id) values (1, 2);
insert into product_category (category_id, product_id) values (2, 1);
insert into product_category (category_id, product_id) values (3, 3);
insert into product_category (category_id, product_id) values (4, 4);
insert into product_category (category_id, product_id) values (5, 5);

select * from supplier;
insert into supplier (id, active, brand, cnpj, company_name, email, phone, mobile) 
	values (1, 1, 'EstoqueBR', '59.290.637/0001-02', 'Fornecedora Brasileira Ltda.', 'estoquebr@email.com', '(00)1111-1111', '(00)91111-1111');
    
insert into supplier (id, active, brand, cnpj, company_name, email, phone, mobile) 
	values (2, 1, 'Papelaria Nobreza', '51.617.277/0001-07', 'Papelaria E Comércio Nobreza S.A.', 'nobreza@email.com', '(00)2222-2222', '(00)92222-2222');

insert into supplier (id, active, brand, cnpj, company_name, email, phone, mobile) 
	values (3, 1, 'PreçoBaixo Store', '06.591.757/0001-83', 'Distribuidora de Eletrônicos EIRELI', 'precobaixo@email.com', '(00)3333-3333', '(00)93333-3333');
    
insert into supplier (id, active, brand, cnpj, company_name, email, phone, mobile) 
	values (4, 1, 'Magazine Carioca', '14.873.751/0001-27', 'Magazine Carioca Distribuidora S.A.', 'magazine@email.com', '(00)4444-4444', '(00)94444-4444');

insert into client (id, active, birth_date, cpf, email, gender, mobile, name, phone) values (1, 1, '1985-11-11', '614.409.460-19', 'jane@email.com', 'FEMALE', '(00) 91111-1111', 'Mary Jane', '(00) 1111-1111');
insert into client (id, active, birth_date, cpf, email, gender, mobile, name, phone) values (2, 1, '2001-11-11', '109.426.490-36', 'doe@email.com', 'FEMALE', '(00) 92222-2222', 'Jane Doe', '(00) 2222-2222');
insert into client (id, active, birth_date, cpf, email, gender, mobile, name, phone) values (3, 1, '1995-11-11', '528.894.080-00', 'maria@email.com', 'MALE', '(00) 93333-3333', 'José Maria', '(00) 3333-3333');
insert into client (id, active, birth_date, cpf, email, gender, mobile, name, phone) values (4, 1, '1975-11-11', '970.053.840-08', 'paul@email.com', 'MALE', '(00) 94444-4444', 'Sean Paul', '(00) 4444-4444');
insert into client (id, active, birth_date, cpf, email, gender, mobile, name, phone) values (5, 1, '1999-11-11', '632.500.000-51', 'cesar@email.com', 'MALE', '(00) 95555-5555', 'Paulo César', '(00) 5555-5555');
insert into client (id, active, birth_date, cpf, email, gender, mobile, name, phone) values (6, 1, '2002-11-11', '699.654.060-73', 'silva@email.com', 'FEMALE', '(00) 96666-6666', 'Carla Silva', '(00) 6666-6666');

insert into product_stock (id, quantity, product_id) values (1, 50, 1);
insert into product_stock (id, quantity, product_id) values (2, 20, 2);
insert into product_stock (id, quantity, product_id) values (3, 30, 3);
insert into product_stock (id, quantity, product_id) values (4, 10, 4);
insert into product_stock (id, quantity, product_id) values (5, 13, 5);

# update sequency tables values

update category_seq SET next_val = 6;
update client_seq SET next_val = 6;
update entry_note_seq SET next_val = 6;
update entry_note_item_seq SET next_val = 6;
update product_seq SET next_val = 6;
update product_stock_seq SET next_val = 6;
update supplier_seq SET next_val = 6;

# create triggers for stock table

DELIMITER $
CREATE TRIGGER TRG_I_PRODUCT AFTER INSERT
ON product
FOR EACH ROW
BEGIN
	insert into product_Stock (quantity, product_id) values (0, NEW.id);
END$

DELIMITER $
CREATE TRIGGER TRG_I_ENTRY_NOTE_ITEM AFTER INSERT
ON ENTRY_NOTE_ITEM
FOR EACH ROW
BEGIN
	UPDATE product_stock
    SET quantity = quantity + NEW.quantity
    WHERE product_id = NEW.product_id;
END$
    
DELIMITER $
CREATE TRIGGER TRG_D_ENTRY_NOTE_ITEM AFTER DELETE
ON ENTRY_NOTE_ITEM
FOR EACH ROW
BEGIN
	UPDATE product_stock
    SET quantity = quantity - OLD.quantity
    WHERE product_id = OLD.product_id;
END$

DELIMITER $
CREATE TRIGGER TRG_U_ENTRY_NOTE_ITEM AFTER UPDATE
ON ENTRY_NOTE_ITEM
FOR EACH ROW
BEGIN
	UPDATE product_stock
    SET quantity = quantity - (OLD.quantity - NEW.quantity)
    WHERE product_id = NEW.product_id;
END$

    