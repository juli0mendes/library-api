# AGENTS: library-api

Purpose: give an AI coding agent the minimal, concrete knowledge to be productive in this Spring Boot repository.

Quick start (commands)
- Build: mvn -DskipTests=false clean package
- Run app locally (connects to Postgres at jdbc:postgresql://localhost:5432/library): mvn spring-boot:run
- Run tests: mvn test
- Quick Docker DB (see `docker-quick-commands.md`):
  - docker network create library-network
  - docker run --name librarydb -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=library --network library-network postgres:16.3

Big picture (architecture)
- Spring Boot application (`src/main/java/io/github/juli0mendes/library/Application.java`) — single module, conventional component scanning.
- Persistence: Spring Data JPA repositories in `repository/` (extend `JpaRepository`) backed by PostgreSQL.
- Custom DB wiring: `DatabaseConfiguration.java` registers a HikariCP `DataSource` (pool settings are defined in code, not only properties).
- Entities live in `model/` (Author, Book, BookGender + BookGenderConverter). Domain objects map to Portuguese-named tables/columns (e.g. table `autor`, `livro`, column `titulo`).

Project-specific conventions & gotchas
- Java 21 and Spring Boot 3.3.1 (see `pom.xml` and `<java.version>`).
- Lombok is used for boilerplate (`@Data`, `@NoArgsConstructor`) but is marked optional and excluded from the spring-boot packaging plugin — IDE/team must enable Lombok.
- UUID primary keys use `@GeneratedValue(strategy = GenerationType.UUID)`.
- Enum persistence: `BookGender` is persisted via `BookGenderConverter` which maps to Portuguese descriptions (this is important when querying or seeding data).
- Some entity fields are intentionally `@Transient` (e.g. `Author.books`) — relations may be managed manually or loaded via repository queries.
- `Book.author` uses `@ManyToOne(cascade = CascadeType.ALL)` — saving a Book may create an Author if the Author is new.
- Hibernate DDL auto is `update` (set in `application.yaml`) — schema evolves at runtime; be cautious in prod.

Tests & developer workflows
- Tests use `@SpringBootTest` and expect a reachable Postgres instance (no in-memory DB). Tests include hard-coded UUIDs in `BookRepositoryTest` and `AuthorRepositoryTest` — tests may fail if DB seed data missing.
- To run tests reliably: start the Docker Postgres (`librarydb`) locally or set `spring.datasource.*` to a test DB and run `mvn test`.

Integration points
- PostgreSQL driver is runtime-scoped in `pom.xml` — production jar expects an external DB.
- HikariCP settings live in `DatabaseConfiguration.java`; timeouts/pool-size are configured in code.
- Docker helper commands are in `docker-quick-commands.md` (use the explicit commands there to start `librarydb` and `pgadmin4` on `library-network`).

Files to inspect first (examples)
- `pom.xml` — dependencies, Java version, spring-boot plugin
- `application.yaml` — datasource URL and Hibernate settings
- `DatabaseConfiguration.java` — Hikari settings and DataSource bean name
- `model/Book*` and `model/Author.java` — domain mapping and column names
- `model/BookGenderConverter.java` — enum DB mapping
- `repository/*Repository.java` — JPA repositories used throughout
- `src/test/...` — shows how tests expect DB state (hard-coded UUIDs, cascade behavior)

Agent tips (actionable)
- When writing migrations/DDL or SQL seeds, use Portuguese column names (e.g. `titulo`, `nome`, `genero`) and descriptions used by `BookGenderConverter` (e.g. `FICCAO`, `FANTASIA`).
- Prefer using the provided `DatabaseConfiguration` or update `application.yaml` to change DB connection. Do not assume an in-memory DB.
- Before modifying entity mappings, search tests for hard-coded UUIDs in `src/test` and update tests accordingly.
- Check `docker-quick-commands.md` for reproducible local DB setup; recommend starting `librarydb` before running tests or the app.

If in doubt: run `mvn -X test` to get diagnostic logs and `tail -n +1 target/surefire-reports/*.txt` to inspect failing tests' outputs.

Contact points in repo: Application.java, DatabaseConfiguration.java, application.yaml, docker-quick-commands.md, model/, repository/, src/test/.

