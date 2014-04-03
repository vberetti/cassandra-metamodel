CREATE TABLE A1 (
	first varchar,
	second int,
	third timestamp,
	
	secondary_index_column varchar,

    underScore_case varchar,
    camelCase varchar,
    "caseSensitive" varchar,
    
    typeA ascii,
    typeB bigint,
    typeC blob,
    typeD boolean,
/*    typeE counter, */
    typeF decimal,
    typeG double,
    typeH float,
    typeI int,
    typeJ text,
    typeK timestamp,
    typeL uuid,
    typeM varchar,
    typeN varint,
    typeO timeuuid,
    PRIMARY KEY (first, second, third),
);

CREATE INDEX A1_idx ON A1 (secondary_index_column);

CREATE TABLE A2 (
	id int,
	name varchar,
	birth_date timestamp,
    PRIMARY KEY (id),
);

CREATE TABLE CUSTOMER (
	id int,
	firstname varchar,
	lastname varchar,
	email varchar,
	birth_date timestamp,
    PRIMARY KEY (id),
);

CREATE TABLE CUSTOMER_ORDERS (
	customer_id int,
	order_id int,
	total decimal,
    PRIMARY KEY (customer_id, order_id),
);
