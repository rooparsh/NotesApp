# NotesApp

NotesApp is a project which demonstrates the power of Kotlin's Ktor in developing powerful REST APIs with all basic as
well as advanced features.

Project is live at [Heroku Link](https://notes-ktor-app.herokuapp.com/).


### Status: 🚧 In progress 🚧

# Requirements
1. IntelliJ Idea


## Architecture

Ktor Controller (http) -> Route (business) -> Repository (data)

## Features

1. Create Notes
2. Update Notes
3. Fetch Notes
4. Delete Notes
5. JWT Authentication
6. Ktorm ORM for DB + Postgress
7. Dependency Injection using Koin
8. Automatic and easy deployment to Heroku


#### Coming soon -:

1. Pagination
2. Sorting results using parameters
3. Unit Testing
4. Middlewares for Validations
5. Searching


## Installation

Use IntelliJ IDEA, community or enterprise edition to open the project and
follow [these steps](https://ktor.io/docs/intellij-idea.html#run_app) to run the Application.

Notes -: Customise the application.conf file. Fill your own
```secret, audience, issuer, realm``` to configure the [JWT](https://ktor.io/docs/jwt.html#jwt-settings).
Similarly,specify your port and host inside the [application.conf](https://ktor.io/docs/configurations.html#hocon-file)
file in which you want to run the server. Also mention you database connection string in key ```jdbc_db_url```

You can use whatever db you are comfortable in MySql, Postgres.

## Usage

### Notes API

1. ##### Create a new note

```http
POST http://host:port/note
Content-Type: application/json

{
  "note": "your-note",
}
```

2. ##### Fetch all notes

```http
GET http://host:port/notes
```

3. ##### Fetch a particular note

```http
GET http://host:port/notes/{id}
```

4. ##### Delete a particular note

```http
DELETE http://host:port/notes/{id}
```

5. ##### Update a particular note

```http
PUT http://host:port/notes/{id}
```

### Onboarding

1. ##### Register

```http
POST http://host:port/register
Content-Type: application/json

{
  "username": "username",
  "password": "password"
}
```

2. ##### Login

```http
POST http://host:port/login
Content-Type: application/json

{
  "username": "username",
  "password": "password"
}

```

### Multipart

1. ##### Open
```http
GET http://host:port/open

```

2. ##### Download
```http
GET http://host:port/download

```