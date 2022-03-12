drop table if exists clients2;
drop table if exists account_types;
drop table if exists accounts2;

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
	account_type_id INTEGER not null,
	amount INTEGER not null default 0,
	CONSTRAINT fk_client FOREIGN KEY(client_id) REFERENCES clients (id)ON DELETE CASCADE, -- FOREIGN key
	CONSTRAINT fk_account_type FOREIGN KEY(account_type_id) REFERENCES account_types (id) ON UPDATE CASCADE-- FOREIGN KEY
	
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
('Olaf','Marifrend-Sergei');

insert into account_types(account_type)
values
('RRSP'),
('High-Interest-Savings'),
('TFSA'),
('Chequing');

insert into accounts (client_id,account_type_id,amount)
values 
(1,1,10000),
(3,2,500),
(10,4,1030),
(1,2,500);

select * from clients;
select * from account_types;
select * from accounts;


delete from clients2 where id= 1;
select * from accounts;
