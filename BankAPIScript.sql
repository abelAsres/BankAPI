drop table if exists accounts;
drop table if exists clients;
drop table if exists account_types;

create table clients(
	id SERIAL primary key,-- SERIAL is a special datatype that will automatically increment itself
						  -- SERIAL is an autoincrementing INTEGER
	first_name VARCHAR(15) not null ,
	last_name VARCHAR(20) not null
);

create table account_types(
id SERIAL primary key,
account_type VARCHAR(25)
);

create table accounts(
	id SERIAL primary key,
	client_id INTEGER not null,
	account_type_id INTEGER default 1,
	balance INTEGER not null default 0,
	CONSTRAINT fk_client FOREIGN KEY(client_id) REFERENCES clients (id)ON DELETE CASCADE, -- FOREIGN key
	CONSTRAINT fk_account_type FOREIGN KEY(account_type_id) REFERENCES account_types (id)on delete restrict-- FOREIGN KEY
	
);

insert into clients (first_name,last_name)
values 
('Anita-Amanda','Huginkiss'), 
('Al','Coholic'),
('Bart','Simpson'),
('Moe','Szyslak'),
('Oliver','Klozoff'),
('I.P.','Freely'), 
('Jacques','Strap'),
('Seymour','Butz'),
('Mike','Rotch'),
('Hugh','Jass'),
('Ivana','Tinkle'),
('Ahmed','Adoudi'),
('Olaf','Marifrend-Sergei'),
('Bea','O''Proplam');

insert into account_types(account_type)
values
('RRSP'),
('High-Interest-Savings'),
('TFSA'),
('Chequing');

insert into accounts (client_id,account_type_id,balance)
values 
(1,1,10000),
(3,2,500),
(10,4,1030),
(1,2,500),
(3,1,10000),
(3,2,500),
(10,4,1030),
(3,4,500),
(10,1,1030),
(10,2,1030),
(10,3,1030);

select * from clients;
select * from account_types;
select * from accounts;

SELECT clients.first_name, clients.last_name, account_types.account_type, accounts.balanceFROM accounts JOIN clients ON accounts.client_id = clients.id JOIN account_types ON account_types.id = accounts.account_type_idWHERE accounts.client_id = 3;
