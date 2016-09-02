# AssignmentMozio

It stores patients data in sqlite.
The assignemnt has one test case writtern in ExampleUnitTest
The data is stored in form of json which can be used to send and recieve data over REST API

Example Patient JSON will look like 

[
  {
    "age": "31-45",
    "gender": "Female",
    "name": "shs",
    "symptoms": [
      {
        "name": "have migraines",
        "value": "true"
      }
    ]
  },
  {
    "age": "31-45",
    "gender": "Male",
    "name": "c",
    "symptoms": [
      {
        "name": "Use hallucinogenic drugs",
        "value": "true"
      }
    ]
  },
  {
    "age": "16-30",
    "gender": "Female",
    "name": "maneet",
    "symptoms": [
      {
        "name": "have migraines",
        "value": "true"
      }
    ]
  },
  {
    "age": "1-15",
    "gender": "Male",
    "name": "parag",
    "symptoms": [
      {
        "name": "have migraines",
        "value": "true"
      },
      {
        "name": "Use hallucinogenic drugs",
        "value": "true"
      }
    ]
  }
]
