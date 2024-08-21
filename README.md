# modernBank

## Application deployment

### Backend services

1. Set-up Keycloak server

    - Move to `backend/keycloak` folder
    - Run `start-keycloak.sh` to create and run Keyclock on a container (keycloack initial configuration found in `data` folder)
    - Keycloak administrator credentials are `admin:admin`
    - This project realm name: `moderbank`

2. Set-up Party backend server

    - Move to `backend/party` folder
    - Run `gradle build`
    - Run `podman build -f src/main/docker/Containerfile -t party-backend:latest .` to create docker image
    - Edit `.env` file to set-up MongoDB root credentials and more important, configure IP address of keycloack running server
    - Run `podman-compose -f infrastructure/party-container-infrastructure.yml up -d` to run both containers (server and database)

### Teller application

1. Set-up Teller UI

    - Move to `frontend/teller/ui` folder
    - Run `ng build` to build the Angular front-end application. The built artifacts will be stored in the `dist/` directory.
    - Run `cp -R dist/ui/browser/* ../server/src/main/resources/META-INF/resources` to copy `dist/ui/browser/` content to `META-INF\resources` folder in front-end server project folder

2. Set-up UI server

    - Move to `frontend/teller/server` folder
    - Run `gradle quarkusDev` to start microservice in development mode

### Test the application

1. Open a browser on `http://localhost/8080`
2. Use `johndoe:password` credentials to sign-in
3. Test Party search functionalities 

