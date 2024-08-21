docker run --name keycloak_for_testing -p 9000:8080 \
       -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin \
       -v $PWD/data:/opt/keycloak/data/import \
       quay.io/keycloak/keycloak:latest \
       start-dev --import-realm