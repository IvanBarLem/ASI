# ASI Project

## Requirements

- Node 8+.
- Java SE 8+.
- Maven 3+.
- MySQL 8+.

## Database creation

```
Start Mysql server if not running (e.g. mysqld).

mysqladmin -u root create asiproject
mysqladmin -u root create asiprojecttest

mysql -u root
    CREATE USER 'asi'@'localhost' IDENTIFIED BY 'asi';
    GRANT ALL PRIVILEGES ON asiproject.* to 'asi'@'localhost' WITH GRANT OPTION;
    GRANT ALL PRIVILEGES ON asiprojecttest.* to 'asi'@'localhost' WITH GRANT OPTION;
    exit
```

## Run

```
cd backend
mvn sql:execute (only first time to create tables)
mvn spring-boot:run

cd frontend
npm install (only first time to download libraries)
npm start
```
