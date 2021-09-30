-- Indexes for primary keys have been explicitly created.

DROP TABLE User;
DROP TABLE Transport;
DROP TABLE TransportType;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
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