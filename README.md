# restful-spring [![Build Status](https://travis-ci.org/Raul6469/restful-spring.svg?branch=master)](https://travis-ci.org/Raul6469/restful-spring)

Experimenting build of a RESTful api with Spring and JPA data persistence

## Installation
- IntelliJ IDEA recommandé
- [Maven compiler](https://maven.apache.org/download.cgi)
- [MySQL](https://dev.mysql.com/doc/refman/5.7/en/windows-installation.html)
- Postman pour tester les API

## Paramétrage
Créer une base de données SQL dans MySQL avec
username: `springuser`
password: `password`

## Compilation
``` bash
$ mvn install
```

## Tests
``` bash
$ mvn test
```

## Utilisation

Lancer la cible `Application` dans IntelliJ

### Obtenir liste personnes enregistrées
GET `/people` : obtenir la liste des personnes enregistrées

### Enregistrer nouvelle personne
POST `/people`
avec payload suivant 

``` json
{
    "firstName": "Victor",
    "lastName": "Raul"
}
```