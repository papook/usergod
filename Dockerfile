FROM payara/server-web:6.2024.5-jdk17
COPY target/usergod.war $DEPLOY_DIR