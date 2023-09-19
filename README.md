[![License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

## Micronaut POC producer/consumer

This proof of concept (POC) demonstrates how to create a service with SQS, DynamoDB using Micronaut.

## Component AWS Configuration
- [SQS](https://aws.amazon.com/sqs/?nc1=h_ls)
- [DynamoDB](https://aws.amazon.com/dynamodb/?nc1=h_ls)
---
## Run
- Requisites:
  - Add environment variable ```export STG=some```
  - Create table in DynamoDB ```counter```
    - Create schema:
      - ```Partition key = pointer(S)```
      - ```Sort key = countNumber(N)```
      - ```Attribute = c(N)```
    - Add Record:
      - ```pointer(S) = key```
      - ```countNumber(N) = 0```
      - ```c(N) = 0```
  - Create a Standard Queue in SQS called ```counter-sqs```

To deploy you application you can run: ```./gradlew run```

---
## Testing
### Local
- Requisites:
    - Gradle 8.2.1
    - Java 17
    - Docker
    - Executes for unit test:
        - ```git clone https://github.com/denjossal/micronaut-producer-consumer```
        - ```cd micronaut-producer-consumer```
        - ```./gradlew test```
    - (Optional) check the enviroment variable 'STG'


<!-- MARKDOWN LINKS & IMAGES -->
[license-shield]: https://img.shields.io/badge/License-Apache_2.0-blue.svg?style=for-the-badge
[license-url]: https://www.apache.org/licenses/LICENSE-2.0
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/djsalcedo