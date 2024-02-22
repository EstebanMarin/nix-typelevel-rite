# Rock the JVM type-level rite

code_dir = 'path

```bash
docker run -d \
  --restart always \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=postgres \
  -p 5432:5432 \
  -v $(pwd)/resource/db/V001__create_jobs_info_table.sql:/db.v001.sql \
  -v $(pwd)/resource/db/V002__w3_db.sql:/db.v002.sql \
  postgres
```

```bash
❯ http get localhost:8080/api/health/ping
HTTP/1.1 200 OK
Connection: keep-alive
Content-Length: 4
Content-Type: text/plain; charset=UTF-8
Date: Thu, 22 Feb 2024 03:06:34 GMT

pong
```

```bash
❯ http get localhost:8080/api/health/postgres
HTTP/1.1 200 OK
Connection: keep-alive
Content-Length: 121
Content-Type: text/plain; charset=UTF-8
Date: Thu, 22 Feb 2024 03:05:19 GMT

PostgreSQL 16.2 (Debian 16.2-1.pgdg120+2) on aarch64-unknown-linux-gnu, compiled by gcc (Debian 12.2.0-14) 12.2.0, 64-bit
```

## run migration

``` bash
$> docker exec -it <container> psql -U postgres
$> \c
$> \i db.v001.sql
```