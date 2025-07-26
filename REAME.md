# Command サーバのみ起動テストステップ
```bash
docker compose down -v
docker compose up test_command_postgres -d
mvn flyway:migrate
mvn clean package
docker compose up -d --build
```