# melix-men APP

- Melix-men REST API
    - [Intro](#API overview)
    - [Mutants](#mutants)
    - [Stats](#stats)

## API overview

It was developed in Java 8 with Spring, using Heroku to deployment, [RedisLabs](https://app.redislabs.com/#/bdbs) to save and show statitcs about the use of our API and MongoDB to persist the DNA.

The API supports HTTP and HTTPS. Examples here are provided using HTTPS.

### Libraries

- spring-boot-starter-data-redis
- spring-boot-starter-data-mongodb
- lombok
- spring-boot-starter-test
- junit

## Endpoints

| URL   | https://melix-men.herokuapp.com/  |
|:------|:----------------------------------|

| Endpoints      | description                                                                    | 
|:---------------|:-------------------------------------------------------------------------------|
| POST `/mutants`     | give us the possibility to recognize if the human is mutant with the DNA given.|
| GET  `/stats`       | give us the statistics about mutants in out human enviroment                   | 

## Mutants

#### Request Param

| Endpoints      | Param | Required | Type    | Example                                                          | 
|:---------------|:------|----------|---------|------------------------------------------------------------------|
| POST `/mutants`| dna   | True.    | String[]| {"dna": ["ATGCAA","CAGAGC","TTTTAT","AGAGGG","GCGTGA","TCACTG"]} |

#### Response

| Endpoints      | Response                                                 | 
|:---------------|:---------------------------------------------------------|
| POST `/mutants`| HTTP Status 200 in case of DNA mutant has been found.    | 
| POST `/mutants`| HTTP Status 403 in case of DNA non-mutant has been found.|

## Stats

#### Request Param

| Endpoints      | Param | Required | Type    | Example                                                          | 
|:---------------|:------|----------|---------|------------------------------------------------------------------|
| GET `/stats`  | -   | -    |- | - |

#### Response

| Endpoints      | Response                                                 | 
|:---------------|:---------------------------------------------------------|
| GET `/stats`| {"count_mutant_dna": 15, "count_human_dna": 32, "ratio": 0.5 }    | 

## HLD

![Flow Diagram](https://github.com/nicolasksq/melix-men/blob/master/Diagram.png?raw=true)



