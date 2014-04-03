CREATE TABLE saMple (
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

CREATE INDEX sample_idx ON sample (secondary_index_column);