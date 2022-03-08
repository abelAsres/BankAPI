drop table if exists clients;
drop table if exists account_types;
drop table if exists accounts;

create table clients (
	id SERIAL primary key,-- SERIAL is a special datatype that will automatically increment itself
						  -- SERIAL is an autoincrementing INTEGER
	first_name VARCHAR(15) not null ,
	last_name VARCHAR(20) not null
);

create table account_types(
id SERIAL primary key,
account_type VARCHAR(10)
);

insert into clients (first_name,last_name)
values 
("Anita-Amanda","Huginkiss"), 
("Al","Coholic"),
("Bart","Simpson"),
("Moe","Szyslak"),
("Oliver","Klozoff"),
("I.P.","Freely"), 
("Jacques","Strap"),
("Seymour","Butz"),
("Mike","Rotch"),
("Hugh","Jass"),
("Ivana","Tinkle"),
("Ahmed","Adoudi"),
("Olaf","Marifrend-Sergei");

insert into account_types
values
("RRSP"),
("High-Interest-Savings"),
("TFSA"),
("Chequing ")

create table accounts(
	id SERIAL primary key,
	client_id INTEGER not null,
	account_type_id INTEGER not null,
	amount INTEGER not null default 0,
	CONSTRAINT fk_client FOREIGN KEY(client_id) REFERENCES clients (id) ON DELETE cascade, -- FOREIGN key
	CONSTRAINT fk_account_type FOREIGN KEY(account_type_id) REFERENCES account_types (id) ON DELETE CASCADE -- FOREIGN KEY
	
);

