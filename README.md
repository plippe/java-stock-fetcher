# Java Stock Fetcher
This project contains multiple Akka services that request stock values from
Yahoo and stores them in a database. Most of the logic is divided to be imported
via Maven and reused in another project.

### Running the application
This project currently uses a postgres database. The easiest way to get setup is
by running the following docker command. It will spin up a postgres instance,
set the appropriate credentials, and create the tables.

```bash
docker run \
  --name java-stock-fetcher-postgres \
  --env POSTGRES_USER=admin \
  --env POSTGRES_PASSWORD=admin \
  --env POSTGRES_DB=secret \
  --publish 5432:5432 \
  --volume $PWD/store/sql:/docker-entrypoint-initdb.d \
  --detach \
  postgres
```

Once your database is up and running, you will need to start the different akka
systems.

```bash
mvn install

mvn exec:java -pl store_akka
mvn exec:java -pl market_provider_akka
mvn exec:java -pl dispatcher_akka
```

This should download the value of the symbols found in `dispatcher_akka`'s
`application.conf` every minute.
