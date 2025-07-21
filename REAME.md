# Command サーバのみ起動テストステップ
bash```
docker compose down -v
docker compose up test_command_postgres -d
mvn flyway:migrate
mvn clean package -DskipTests               #DBコンテナ起動していないと、総合テスト実行できない。 (ModelMapperTest)
docker compose up -d --build
```