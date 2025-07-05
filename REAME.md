# Command サーバ起動ステップ

1. Docker コンテナの起動（DB 運用）−JOOQ codegen に用意する −

```bash
docker-compose up -d --build
```

2. SpringBoot サーバ起動
```bash
mvn spring-boot:run
```