#!/bin/bash

echo "Bringing down the Curium application"
docker-compose -f docker-compose-local.yml down

echo "Triggering clean build of the applicaiton"
mvn clean install -DskipTests

echo "Bringing up the Curium application"
docker-compose -f docker-compose-local.yml up --build -d

echo "Removing any dangling images created during the build"
docker rmi $(docker images -f "dangling=true" -q)