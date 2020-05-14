# Simple time tracker REST API

Demo project with Spring boot CRUD based REST API for creating hierarchical task structure and managing status and spent time

## Getting Started


### Prerequisites


```
Docker or Java IDE with Gradle support
```

### Starting

Start using docker-compose and H2

```
docker-compose up -d timetracker_api
```

for version with PostgreSQL (timetracker_api service must be started with delay due to dependency on db)
```
docker-compose build --build-arg DB_TYPE=pg timetracker_api
docker-compose up timetracker_db
docker-compose up timetracker_admin
docker-compose up timetracker_api
```

or using Gradle (JDK must be installed)

```
gradlew build runJar
```

**or using Java IDE**

Application should be available on http://localhost:8080/api/v1.0/tasks with the h2 database by default.

Access Swagger UI from http://localhost:8080/api/swagger-ui.html. Also it is possible to use API import (Postman) from http://localhost:8080/api/v3/api-docs, when instance is up.

Access H2 console from http://localhost:8080/h2-console (use `./db/timetracker` instead of default `~/test` path)

### Using docker
Access to pgadmin http://localhost:5050 (check connection details in the docker-compose.yml, username, password and container_name for hostname as it will be linked in docker network). Initial credentials also available in the docker-compose.yml

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
