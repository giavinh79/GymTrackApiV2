# GymTrack Spring Boot REST API

---

## Description
This is the second iteration of the API for the GymTrack application built with Java Spring Boot and PostgreSQL.
Currently being built and not close to being production ready.

<a href="https://todo.com">See the full-stack application here</a>

The API itself is deployed [here](https://todo.com) and documented [here](https://todo.com) using the OpenApi (Swagger) Specification.

## Running locally
GymTrack is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). It uses PostgreSQL as its data store which can be run locally using [Docker](https://www.docker.com/) (see `docker-compose.yml` file). After Maven builds, run the server from your favourite IDE!

The API will run locally at: http://localhost:8080/

The relational database will run locally at: http://localhost:5432/

A useful GUI tool for PostgreSQL [pgAdmin](https://www.pgadmin.org/) will run locally at: http://localhost:5050/

To connect to the PostgreSQL instance, create new server and pass `postgres` as the network - user credentials can be found in the `docker-compose.yml`).

### Prerequisites
The following items should be installed in your system:
* Java 17 or newer (full JDK not a JRE).
* Git (https://help.github.com/articles/set-up-git)
* Maven (https://www.baeldung.com/install-maven-on-windows-linux-mac)

## Project Structure
This Spring Boot project follows a typical N-tier architecture and organizes folder firstly by feature/domain (as opposed to type).

## Database

The PostgreSQL schema ER diagram can be found here (need to login & could be outdated): https://lucid.app/lucidchart/dd9442a0-7dd9-4202-a0d6-0ac82ad5bff2/edit?invitationId=inv_613c1097-affe-4ccf-9781-51b230058742

![image](https://user-images.githubusercontent.com/24909563/154824860-209d0041-9903-4d9c-a382-f95745e1b23d.png)

The project uses [Flyway](https://flywaydb.org/) as version control for the database (managing DB migrations).

## CI/CD & Deployment Process (TO DO)

CI/CD to be handled using GitHub actions.

Spring Boot server to be deployed on AWS Lightsail and will use [Supabase](https://supabase.com/) for a fully-managed PostgreSQL cloud instance.

## Other To Dos

Maybe look into Hexagonal Architecture (for fun)
