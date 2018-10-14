# ANDDigital

AND Digital phone API technical test

## Getting Started

### Installing

To install the application run the following command

```
mvn clean install
```

To run the application from the command line use the following command

```
java -jar target/phoneAPI-1.0.0.jar
```

To get a list of all users use the following URI. This can be used to get the id's required to get 

```
localhost:8080/customers
```

To get a single user use the id from the user in the list of users and use the following URI

```
localhost:8080/customers/{id}
```

To activate a phoine number get the customer and phone number id's and use the following URI

```
localhost:8080/customers/{id}/phone_number/{phone_number_id}/activate
```

## Running the tests

Test data is stored in the following location

```
phoneAPI/src/main/resources/data.sql
```

This data will be loaded into a in memory h2 database

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Lucas Urry**