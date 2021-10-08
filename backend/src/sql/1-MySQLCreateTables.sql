-- Indexes for primary keys have been explicitly created.

DROP TABLE User;
DROP TABLE Transport;
DROP TABLE TransportType;
DROP TABLE Activity;
DROP TABLE Accommodation;


CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(60) NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT EmailUniqueKey UNIQUE (email)
) ENGINE = InnoDB;

CREATE TABLE TransportType (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	CONSTRAINT TransportTypePK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Transport (
    id BIGINT NOT NULL AUTO_INCREMENT,
    transportTypeId BIGINT NOT NULL,
    date DATETIME NOT NULL,
    origin VARCHAR(60) NOT NULL,
    destination VARCHAR(60) NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT TransportTransportTypeIdFK FOREIGN KEY(transportTypeId)
        REFERENCES TransportType (id)
) ENGINE = InnoDB;

CREATE TABLE Activity (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	CONSTRAINT ActivityPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Accommodation (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	CONSTRAINT AcommodationPK PRIMARY KEY (id)
) ENGINE = InnoDB;