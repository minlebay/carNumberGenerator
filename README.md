# carNumberGenerator
simple test task

There are two way to generate car numbers:
1. next number - endpoint /number/next
2. random number - endpoint /number/random

First, the numeric characters are iterated, and only after that the alphabetic characters are iterated. 
Numbers are not repeated.

For convenience, put the docker-compose file in the db_docker package.
Tests use second database, select maven profile before running them.
