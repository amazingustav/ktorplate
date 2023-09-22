[English README](/README.en-US.md)
# Ktorplate - Template para projetos Ktor

### Tech stack:
* Kotlin (JDK 17)
* [Koin](https://insert-koin.io/)
* [Ktor](https://ktor.io)
* [JetBrains Exposed](https://github.com/JetBrains/Exposed)
* [Flyway](https://flywaydb.org/)
* PostgreSQL

Este projeto obedece as regras da [Arquitetura Limpa (Clean Architecture)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) e está organizado da seguinte maneira:
```bash
|-- application -> Módulo principal, responsável por injeções de dependências e contextos. Enxerga e orquestra todos os outros módulos
|-- persistence -> Faz a integração e interface com o banco de dados ou qualquer outro tipo de armazenamento de dados. Reconhece os módulos "entity" e "usecase"
|-- web         -> Web Adapter, onde expomos a porta de entrada da aplicação (API, evento, sockets...). Reconhece os módulos "entity" e "usecase"
|-- usecase     -> Regras da aplicação. Reconhece apenas o módulo "entity".
|-- entity      -> Entidades e regras de negócio. Não reconhece nenhum módulo. É o miolo da arquitetura
```

### Como executar

* Para inicializar o banco de dados e a API, basta executar o seguinte comando:
    * Caso queira acompanhar os logs, apenas remova o argumento `-d`
```$xslt
docker-compose up -d
```

* Para encerrar o banco de dados e a API, basta executar o seguinte comando:
```$xslt
docker-compose stop
```

**OBS**: É necessário estar no mesmo nível de pasta do arquivo `docker-compose.yml` para executar os comandos acima.


### Versionamento da Base
Todas as alterações na base são versionadas com o [flyway](https://flywaydb.org/), então a cada alteração (DDL/DML) da base, é necessário adicionar um novo arquivo na pasta `resources/db/migration` contendo sua alteração. O build do gradle se encarregará de aplicar as alterações graças ao plugin do flyway.
