# Teller UI in Angular
## How to create the application from scratch

### Basic installation for development

1. Angular CLI

    ```
    npm install -g @angular/cli
    ```

2. Bootstrap

    ```
    npm install bootstrap@5.3.3 --save
    npm install bootstrap-icons --save
    ```

3. Bootstrap widgets

    ```
    ng add @ng-bootstrap/ng-bootstrap
    ```

4. Other libraries

    ```
    npm install date-fns --save
    ```

5. Other configurations

    Update `angular.json` file for setting output assets folder and bootstrap configurations
    
    ```
    "assets": [
        {
            "glob": "bootstrap-icons.svg",
            "input": "node_modules/bootstrap-icons/",
            "output": "/assets/icons/"
        },
        {
            "glob": "**/*",
            "input": "public",
            "output": "/assets"
        }
    ]

    "styles": [
        "node_modules/bootstrap/scss/bootstrap.scss",
        "node_modules/bootstrap-icons/font/bootstrap-icons.css",
        "src/styles.scss"
    ]
    
    "scripts": [
        "node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"
    ]
    ```

    Proxy configuration

    - This project uses a `proxy.conf.json` file to redirect API request to localhost server on deployment mode. On production mode project is configured to run both Angular and APR resources on the same microservice.
    - Proxy configuration has also been changed to point that proxy configuration file in the `angular.json` file, so you can use `ng serve` command for testing application in development mode without the need to explicit the proxy file (as in `ng serve --proxy-config src/proxy.conf.json` commnand)