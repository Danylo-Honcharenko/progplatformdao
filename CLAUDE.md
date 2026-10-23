# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

Java 21, Maven (no wrapper in this repo — use system `mvn`). Spring Boot parent 4.1.1, but this is a plain library (no main class).

- `mvn test` — run all tests (JaCoCo report generated in the test phase)
- `mvn -Dtest=CourseDaoImplTest test` — single test class (`-Dtest=CourseDaoImplTest#method` for one method)
- `mvn install` — **required after any change** so the sibling projects pick it up from the local Maven repo

No formatter or linter is configured.

## Multi-repo architecture

This is `ProgPlatformDAO` (`org.ua.fkrkm`, `1.0-DEV`), the data-access layer of three sibling Maven projects. Consumed by `../prohplatform-pylearn-server` (REST API), which wires DAO beans explicitly in its `DaoConfiguration` (not component-scanned). Shared DTOs live in `../progplatformclientlib`. A new DAO here also needs a bean declared in the server.

Package is `org.ua.fkrkm.proglatformdao` (typo "proglatform", not "progplatform") — keep it.

## Structure

- `dao/*DaoI` — interfaces; `dao/impl/*DaoImpl` — implementations. Most extend `ParentDaoImpl<T>` (generic CRUD over Spring `JdbcTemplate`/`NamedParameterJdbcTemplate`): a subclass constructor configures it via setters — `setTableName`, `setParam` (`:name, :created`), `setValues` (column list), `setValuesForUpdate`, `setRowMapper`. Insert/update bind via `BeanPropertySqlParameterSource`, so entity property names must match the `:param` names. Entity-specific queries are written as raw SQL in the impl.
- `mappers/` — `RowMapper`s per entity (including composite ones like `ModuleCompleteMapper`; `TestMapper` leaves questions empty, `TestDaoImpl` fills them).
- `entity/` — Lombok entities (`BaseEntity` base); `entity/view` and `entity/ext` hold joined/aggregated read models.
- `TestDaoImpl` — the exception to the pattern: a `Test` owns its `Question`s (separate `question` table, `options` stored as JSONB via Jackson). It overrides `create`/`update`/`delete` to write test and questions in one transaction (`TransactionTemplate`), and `getById`/`getAll`/`getByTopicId` load questions in a second query. `update` with `questions == null` keeps existing questions, otherwise replaces them.

## Tests

JUnit 5, in the default package layout `src/test/java/dao` (not mirroring `org.ua...`). `configuration/Database` is a singleton in-memory H2 (`MODE=PostgreSQL`) loaded from `src/test/resources/dbT.sql` — the test schema/data is separate from the server's Flyway migrations, so schema changes must be mirrored in `dbT.sql` by hand (`schema.sql`/`data.sql` also exist there). Load errors are only printed, not thrown, so a broken script shows up as confusing test failures. H2 stores a plain string written to a JSONB column as a JSON string (double-encoded), unlike PostgreSQL — seed JSON in `dbT.sql` with `FORMAT JSON`, and `QuestionMapper` tolerates both forms.

## Conventions

Comments and Javadoc are written in Ukrainian (impl methods use `{@inheritDoc}`; document the contract on the interface); keep that. Commit messages are short lowercase with a prefix (`feature:`, `fix:`, `refactoring:`, `improvements:`, `update:`).
