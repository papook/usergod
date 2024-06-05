#!/bin/bash

CONTAINER_NAME="usergod/payara:latest"

./mvnw clean package
docker buildx build . -t $CONTAINER_NAME
docker run -it -p 8080:8080 $CONTAINER_NAME
