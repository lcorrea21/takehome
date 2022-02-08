
# Take-Home Project

REST application that does the following:
- Register user with first name, last name, email and password.
- Get list of all the user in the system.
- Search a user by email and confirm if the user exists.

## Check List

* MariaDB (Use Xampp).
* Create user in database. Import script takehome.sql (optional)

## Create user and database

```bash
  CREATE DATABASE takehome;
  
  CREATE USER 'kanbandev'@'localhost' IDENTIFIED BY 'K4nb4n$D3v#';
  GRANT ALL PRIVILEGES ON * . * TO 'kanbandev'@'localhost';
  FLUSH PRIVILEGES;
```

#### Import Takehome.sql (optional)

```bash
  mysql -u kanbandev -p takehome < Takehome.sql
```

## API Reference

### Get Token
```http
 curl -X POST http://localhost:8080/authenticate 
  -H "Content-Type: application/json" 
  -d '{"username": "k4nb4n", "password": "k4Nb4nD3v#"}'
```

### Get List Of All Users

```http
  curl http://localhost:8080/api/v1/takehomeproject/users/ListUsers
   -H "Content-Type: application/json"
   -H "Authorization: Bearer **token**"
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_token` | `string` | **Required**.  API token |


### Get User exists
```http
  curl http://localhost:8080/api/v1/takehomeproject/users/isUserExists/{email}
   -H "Content-Type: application/json"
   -H "Authorization: Bearer **token**"
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required** |
| `api_token` | `string` | **Required**.  API token |

### Update user
```http
  curl -X PUT http://localhost:8080/api/v1/takehomeproject/users/UpsertUser
   -H "Content-Type: application/json"
   -H "Authorization: Bearer **token**"
   -d '{
    "id": 1,
    "firstname": "Luis",
    "lastname": "Correa",
    "email": "luis@gmail.com",
    "password": ""
  }'
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_token` | `string` | **Required**.  API token |


## Endpoints

| Name | URL     |     Description            |
| :-------- | :------- | :------------------------- |
| `ListUsers` | `http://localhost:8080/api/v1/takehomeproject/users/ListUsers` | List all users |
| `isUserExists` | `http://localhost:8080/api/v1/takehomeproject/users/isUserExists/{email}` | Search if an user exists by email |
| `UpsertUser` | `http://localhost:8080/api/v1/takehomeproject/users/UpsertUser` | Update an user |
| `get` | `http://localhost:8080/api/v1/takehomeproject/users/get/{usuarioId}` | Get user by Id |
| `save` | `http://localhost:8080/api/v1/takehomeproject/users/save` | Save an user |
| `delete` | `http://localhost:8080/api/v1/takehomeproject/users/delete/{usuarioId}` | Delete an user by ID |
| `Swagger` | `http://localhost:8080/takehome-swagger-ui.html` | Api documentation |

## Running Tests

To run tests, run the following command

```bash
  mvn test
```

