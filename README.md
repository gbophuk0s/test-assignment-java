## Test Assignment Frontend


### Requirements

[Google Doc.](https://docs.google.com/document/d/1AgsMmM0NLO_HybnpRFcQ3ujfyayahfK357If5jSifcs/edit#heading=h.x5h0upabsnvd)


### Prerequisites

You need to have [Docker](https://docs.docker.com/get-docker/) installed for running the application


### Building

Command to build the application
```
./gradlew clean build
```


### Running

Start required environment with command
```
docker compose -f docker-compose.yaml up -d
```
This will start the PostgreSQL database (port 5432)

Run the built jar with command
```
cd build/libs/ && java -jar test-assignment-java.jar
```

**Note** that once the application has been started you can use the ```help``` command to get the list of available commands
