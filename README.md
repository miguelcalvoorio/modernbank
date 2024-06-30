# modernBank

### Basic installation for development

Angular CLI

    npm install -g @angular/cli

Bootstrap

    npm install bootstrap bootstrap-icons --save

Application configuration

    angular.json
    
    "assets": [
        {
            "glob": "bootstrap-icons.svg",
            "input": "node_modules/bootstrap-icons/",
            "output": "/assets/icons/"
        },
        {
            "glob": "**/*",
            "input": "public"
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

