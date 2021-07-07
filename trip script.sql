CREATE DATABASE trips;
USE trips;

CREATE TABLE users(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    email VARCHAR(30),
    role VARCHAR(30), 
    password VARCHAR(256),
    PRIMARY KEY(id)
);

CREATE TABLE country(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(30),
    PRIMARY KEY(id)
);

CREATE TABLE city(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(30),
    country_id INT UNSIGNED,
    PRIMARY KEY(id),
    FOREIGN KEY(country_id) REFERENCES country(id)
);

CREATE TABLE airport(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(40),
    city_id INT UNSIGNED,
    PRIMARY KEY(id),
    FOREIGN KEY(city_id) REFERENCES city(id)
);

CREATE TABLE trip(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    reason VARCHAR(20),
    description VARCHAR(256),
    departure_date DATE,
    arrival_date DATE,
    user_id INT UNSIGNED,
    departure_city INT UNSIGNED,
    arrival_city INT UNSIGNED,
    status VARCHAR(30),
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(departure_city) REFERENCES city(id),
    FOREIGN KEY(arrival_city) REFERENCES city(id)
);

CREATE TABLE flight(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    trip_id INT UNSIGNED,
    departure_airport INT UNSIGNED,
    landing_airport INT UNSIGNED,
    departure_date DATE,
    landing_date DATE,
    PRIMARY KEY(id),
    FOREIGN KEY(trip_id) REFERENCES trip(id),
    FOREIGN KEY(departure_airport) REFERENCES airport(id),
    FOREIGN KEY(landing_airport) REFERENCES airport(id)
);
