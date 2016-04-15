# primenumbersservice

Implemented as a maven project with Spring, in Java8.
Provides tests with junit4.

The service name is /primeNumbers.
It takes two parameters:
- max [mandatory] [int]: prime numbers from 2 to max will be found
- method [optional] [string]:
	-- oldSchool: java 7 style, sequential
	-- java8: java 8 style, no parallel
	-- java8Parallel: java 8 style, parallel (no control on number of threads)
	-- java8ParallelWithThreadingLimit: java 8 style, parallel via a limited number of threads (based on number of cores)

Note: if no method is specified, all methods will be used.

Example: with method

GET http://localhost:8080/primeNumbers?max=10&method=java8
RESPONSE:
[
    {
        "max": 10,
        "method": "java8",
        "result": [
            2,
            3,
            5,
            7
        ],
        "numberOfCores": 8,
        "timeInMilliSeconds": 0,
        "timeInNamoSeconds": 55447
    }
]

Example 2: no method specified

GET http://localhost:8080/primeNumbers?max=10
RESPONSE:
[
    {
        "max": 10,
        "method": "oldSchool",
        "result": [
            2,
            3,
            5,
            7
        ],
        "numberOfCores": 8,
        "timeInMilliSeconds": 0,
        "timeInNamoSeconds": 3946
    },
    {
        "max": 10,
        "method": "java8",
        "result": [
            2,
            3,
            5,
            7
        ],
        "numberOfCores": 8,
        "timeInMilliSeconds": 0,
        "timeInNamoSeconds": 42636
    },
    {
        "max": 10,
        "method": "java8Parallel",
        "result": [
            2,
            3,
            5,
            7
        ],
        "numberOfCores": 8,
        "timeInMilliSeconds": 1,
        "timeInNamoSeconds": 1232156
    },
    {
        "max": 10,
        "method": "java8ParallelWithThreadingLimit",
        "result": [
            2,
            3,
            5,
            7
        ],
        "numberOfCores": 8,
        "timeInMilliSeconds": 0,
        "timeInNamoSeconds": 899739
    }
]
