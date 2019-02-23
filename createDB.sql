DROP TABLE rides_passengers;
DROP TABLE rides;
DROP TABLE passengers;

CREATE TABLE rides (
	ride_id INTEGER CONSTRAINT rides_pk PRIMARY KEY ASC AUTOINCREMENT,
	from_city VARCHAR NOT NULL,
	to_city VARCHAR NOT NULL,
	ride_date DATE NOT NULL,
	ride_time TIME NOT NULL
);

CREATE TABLE passengers (
	phone VARCHAR CONSTRAINT passengers_pk PRIMARY KEY,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	city VARCHAR NOT NULL,
	street VARCHAR NOT NULL,
	apt_number INTEGER NOT NULL,
	rating REAL DEFAULT 0
);

CREATE TABLE rides_passengers (
	ride_id INTEGER,
	passenger_phone VARCHAR,
	ride_rating REAL DEFAULT 0,
	CONSTRAINT rides_passengers_pk PRIMARY KEY (ride_id, passenger_phone),
	CONSTRAINT ride_id_fk FOREIGN KEY (ride_id) REFERENCES rides(ride_id),
	CONSTRAINT passenger_phone_fk FOREIGN KEY (passenger_phone) REFERENCES passengers(phone)
);