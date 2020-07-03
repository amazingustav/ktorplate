[Portuguese README](/README.md)
# Ktorplate - Template Ktor project

### Tech stack:
* Kotlin
* [Koin](https://insert-koin.io/)
* [Ktor](https://ktor.io)
* [Kotlin Exposed](https://github.com/JetBrains/Exposed)
* [Flyway](https://flywaydb.org/)
* PostgreSQL

This project follows [Clean Architecture rules](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and its structure is organized like:
```bash
|-- application -> Principal module, responsable to dependency and context injections. It orchestrate all other modules.
|-- persistence -> Database (or any kind of data storage) interface and integration. It sees "entity" e "usecase" modules.
|-- web         -> Web Adapter, where the entry is exposed (e.g. API, events, sockets...). It sees "entity" e "usecase" modules.
|-- usecase     -> Application rules. It sees only "entity" module.
|-- entity      -> Entities and business rules. This is the core of application. It doesn't use any module, but anyone of them can use it.
```

### How to execute

* To start application and databse, you just have to type this command:
    * If you don't wanna see logs, remove `-d` argument.
```$xslt
docker-compose up -d
```

* To quit both application and database services, just type:
```$xslt
docker-compose stop
```

**PS**: You have to be in the same folder level as `docker-compose.yml` file to execute the commands above.


### Database versioning
Every databse change is versioned with [flyway](https://flywaydb.org/), so when you change db structure (DDL/DML), you have to add a new file into `resources/db/migration` folder with your change content. Gradle build will make sure to apply all changes, using flyway-plugin.

## ISSUES
* There are no tests (eXtreme Go Horse, buddy)
