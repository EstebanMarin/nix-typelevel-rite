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

## run migration

``` bash
$> docker exec -it <container> psql -U postgres
$> \c
$> \i db.v001.sql
```