# GymTrack Spring Boot REST API

## Description

This is the second iteration of the API for the GymTrack application built with Java Spring Boot and PostgreSQL. It is
currently being built and not close to being production ready.

The front-end repository can be found here: https://github.com/GV79/GymTrack.

<a href="https://todo.com">See the full-stack application here</a>

The API itself is deployed [here](https://todo.com) and documented [here](https://todo.com) using the OpenApi (Swagger)
Specification.

### Prerequisites

The following items should be installed in your system:

* Java 17 or newer (full JDK not a JRE).
* Docker
* Git (https://help.github.com/articles/set-up-git)
* Maven (https://www.baeldung.com/install-maven-on-windows-linux-mac)

## Running locally

GymTrack is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built
using [Maven](https://spring.io/guides/gs/maven/). It uses PostgreSQL as its data store which can be run locally
using [Docker](https://www.docker.com/) (see `docker-compose.yml` file).

**DB**

In order to start up PostgreSQL, Redis, and pgAdmin:

```console
test@user:~$ cd db && docker compose up -d
```

- The relational database will run locally at: http://localhost:5432/
- A useful GUI tool for PostgreSQL [pgAdmin](https://www.pgadmin.org/) will run locally at: http://localhost:5050/
- Redis will run locally at: http://localhost:6379

To connect to the PostgreSQL instance from a DB GUI tool (like pgAdmin), create a new server and pass `postgres` as the
network. User credentials can be found in `docker-compose.yml`.

**API**

Need to look into handling secrets via AWS Secrets Manager for production deployments
i.e. [1](https://medium.com/javarevisited/spring-boot-externalized-database-configuration-with-aws-secrets-manager-605f5ea3006a)
, [2](https://dzone.com/articles/integrating-aws-secrets-manager-with-spring-boot)

- The API will run locally at: http://localhost:3030/
    - Build the project with Maven and run it locally in your favourite IDE
    - Make sure to set environment variable `GOOGLE_APPLICATION_CREDENTIALS` in your run configuration pointing to the
      Firebase Private Key file
    - Some routes are protected by Firebase Auth (you can grab the token by logging in to the client app and checking
      network or [alternatively](https://stackoverflow.com/questions/49934701/get-firebase-access-token-in-postman))
- The API documentation (OpenAPI + Swagger) will run at: http://localhost:3030/swagger-ui.html

### Development

- Using [IntelliJ](https://www.jetbrains.com/idea/) IDE
    - **Plugins**
        - [SonarLint](https://plugins.jetbrains.com/plugin/7973-sonarlint)
            - Configure rules in Settings -> Tools -> SonarLint -> Rules
            - Rules I've disabled:
                - @TODO warnings/errors
        - [String Manipulation](https://plugins.jetbrains.com/plugin/2162-string-manipulation)
    - [Format code on save (rearrange, reformat, optimize imports)](https://stackoverflow.com/a/68629786)
    - [Configure hot reloading](https://stackoverflow.com/questions/33349456/how-to-make-auto-reload-with-spring-boot-on-idea-intellij)
        - Settings -> Build, Execution, Deployment -> Compiler -> Build project automatically
        - Settings -> Advanced Settings -> Allow auto-make to start even if...etc.
- Authentication is currently being powered by Google Firebase
    - Ensure you create and setup different Firebase environments for dev vs. prod
- MapStruct is used for transforming entities from one type to another
    - Rebuild project / Recompile `Mapper` file if `MapStruct` did not automatically update yet
- Useful local development commands for database & cache
    - `docker exec -it redis-container-name redis-cli -a your-password FLUSHALL` for clearing local Redis cache
    - `mvn flyway:clean` to clear all PostgreSQL migrations (and thus delete all db data)

## Project Structure

This Spring Boot project follows a typical N-tier architecture and organizes folder firstly by feature/domain (as
opposed to type).

## Database

The PostgreSQL schema ER diagram can be found here (need to login & could be
outdated): https://lucid.app/lucidchart/dd9442a0-7dd9-4202-a0d6-0ac82ad5bff2/edit?invitationId=inv_613c1097-affe-4ccf-9781-51b230058742

![GymTrack Schema](https://user-images.githubusercontent.com/24909563/186131357-83f688af-50f1-44bd-937e-7f0379147a53.png)

The project uses [Flyway](https://flywaydb.org/) as version control for the database (managing DB migrations).

## CI/CD & Deployment Process (TO DO)

CI/CD to be handled using GitHub actions.

Spring Boot server to be deployed on AWS Lightsail and will use [Supabase](https://supabase.com/) for a fully-managed
PostgreSQL cloud instance.

## TO DOs development

- Tech debt:
    - Use GenericJackson2JsonRedisSerializer instead of manually implementing Serializable
        - https://www.baeldung.com/spring-boot-redis-cache
    - Create join table `exercise_image` between `exercise`and `image` as right now, exercises can only support a single
      image - Move UserRoutine controller location - Compress and then store exercise images on AWS S3 rather than use
      wiki URLs - Instead of defining exerciseValueTypeUnit one to many mapping as eager fetch type, create custom JPQL
      @Query to fetch lazily loaded properties for /exercises HTTP GET
      endpoint (https://stackoverflow.com/questions/15359306/how-to-fetch-fetchtype-lazy-associations-with-jpa-and-hibernate-in-a-spring-cont/15360333#15360333)
    - Sync PostgreSQL DB sequences with Hibernate (https://thorben-janssen.com/hibernate-tips-use-custom-sequence/)
    - Validation for all request DTOs
    - Do I really need ServiceImpl and interfaces?
- Improvements:
    - Maybe use something like `hashid` - (convert ids to more obscured but decryptable integer)
    - More verbose API docs - https://www.baeldung.com/spring-rest-openapi-documentation
    - Testing (maybe for testing with database look
      into https://betterprogramming.pub/how-to-test-databases-easily-in-a-spring-boot-application-5ccdfbc6309f)
        - https://github.com/testcontainers/testcontainers-java
    - Monitoring (DataDog vs. New Relic vs. HoneyComb.IO vs. Prometheus + Grafana + Loki)
        - Possible useful references
            - https://reflectoring.io/spring-boot-tracing
            - https://grafana.com/blog/2022/05/04/how-to-capture-spring-boot-metrics-with-the-opentelemetry-java-instrumentation-agent/

## For fun

Maybe look into Hexagonal Architecture
https://www.youtube.com/watch?v=ujb_O6myknY&t=287s
