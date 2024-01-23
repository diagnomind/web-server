# Diagnomind
[![Java CI with Maven](https://github.com/diagnomind/web-server/actions/workflows/ci-maven.yml/badge.svg)](https://github.com/diagnomind/web-server/actions/workflows/ci-maven.yml)

[![Quality Gate Status](https://sonarqube.diagnomind.duckdns.org/api/project_badges/measure?project=web-server&metric=alert_status&token=sqb_a86c0409bcd97e57da58d55b041a4b0afa26738e)](https://sonarqube.diagnomind.duckdns.org/dashboard?id=web-server)

## Description

This is the REST API to interface with the MariaDB database of the Diagnomind backend.

## Installation

To build this project Java 21 is needed, with the following command:
```
$> mvn clean package
```

To test the project execute:
```
$> mvn clean verify
```

To generate Javadoc documentation:
```
$> mvn javadoc:javadoc
```

## Usage

To use this web-server, firstly it is needed to run the [SQL script](src/main/sql/diagnomind.sql) on a mariaDB server, and then run this project with this command (Java 21 necessary):
```
$> java -jar target/web_server-1.0.0.jar
$> SPRING_PROFILES_ACTIVE=1 DB_URL=<URL> DB_PORT=<PORT> DB_USER_NAME=<NAME> DB_USER_PASSWORD=<PASSWORD> java -server -jar target/web_server-1.0.0.jar
```

## Credits

- Qing Yu Jiang Pan
- Diogo Sousa Fernandes
- Gaizka Sáenz de Samaniego Gonzalez
- Asier López Lorenzo
- Eñaut Genua Prieto

## License

This project is licensed under the [AGPLv3+](LICENSE).