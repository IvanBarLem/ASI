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

## If using Docker for mysql

Download mysql image:

```bash
docker pull mysql:8.0.11
```

Start the mysql container:
```bash
docker run --name mysql -p 3306:3306 -v /path/to/your/db/volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=admin -d mysql:8.0.11
```

Once it is running, enter the container:
```bash
docker exec -it mysql bash
```

Then inside the container, access to mysql with credentials:
```bash
mysql -u root -p
```
Create database:
```bash
create database asiproject;
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
