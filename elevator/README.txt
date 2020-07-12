


To run ElevatorTest: 
Do: java -jar elevator-1.0-SNAPSHOT-tests.jar cscie55.hw3.elevator.ElevatorTest

To run AnimalTest (please notice the class name I use is AnimalTest rather than AnimalsTest)
Do: java -jar elevator-1.0-SNAPSHOT-tests.jar cscie55.hw3.zoo.animals.AnimalTest


The structure in src is like below:
[HU-student@localhost src]$ tree
.
├── main
│   └── java
│       └── cscie55
│           └── hw3
│               ├── elevator
│               │   ├── Building.java
│               │   ├── ElevatorFullException.java
│               │   ├── Elevator.java
│               │   ├── Floor.java
│               │   └── Passenger.java
│               └── zoo
│                   ├── animals
│                   │   ├── Animal.java
│                   │   ├── Cheetah.java
│                   │   ├── Gorilla.java
│                   │   ├── Meerkat.java
│                   │   ├── PolarBear.java
│                   │   ├── Zebra.java
│                   │   └── zoo
│                   └── iface
│                       ├── Madable.java
│                       ├── Playble.java
│                       └── Smartable.java
└── test
    └── java
        └── cscie55
            └── hw3
                ├── elevator
                │   └── ElevatorTest.java
                └── zoo
                    ├── animals
                    │   └── AnimalTest.java
                    └── iface



