### Hexlet tests and linter status:
[![Actions Status](https://github.com/packman1783/java-project-99/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/packman1783/java-project-99/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/72f7dcc9e0da19114a27/maintainability)](https://codeclimate.com/github/packman1783/java-project-99/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/72f7dcc9e0da19114a27/test_coverage)](https://codeclimate.com/github/packman1783/java-project-99/test_coverage)

## Description:
Task Manager is a small web application based on Spring Boot framework, it allows you:
 - set tasks
 - assign executors
 - change their statuses

Here we practice the basic principles of building application:
 - creation of entities using ORM and description of relations between them (o2m, m2m)
 - use of resource routing, which allows to unify work with typical CRUD operations
 - registration and authentication mechanism
 - data filtration

You can familiarise yourself with the possibilities of the application here on Render.com [task-manager](https://java-project-99-7ogs.onrender.com)

## Use:
for example:
```
gradle bootRun
```

By default, the server starts at http://localhost:8080, after you can use default

Username: hexlet@example.com

Password: qwerty

*Server Communication Error* appears when the JWT token has expired, you need to logout and log in again.

![task-manager](https://i.ibb.co/ch98kqQ/task-manager.jpg)

You will find the administration dashboard and can explore all available options such as users, tasks, task statuses and labels

![task-manager dashboard](https://i.ibb.co/P6rDHN7/task-manager-dashboard.jpg)

You can also get documentation using the Swagger resource with link http://localhost:8080/swagger-ui/index.html
