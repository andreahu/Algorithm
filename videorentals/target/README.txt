The package I downloaded was hw_4.4


To run BankTester: 
do: java -jar threads-1.0-SNAPSHOT-tests.jar cscie55.hw4.bank.BankTester

To run Animaltest:
Do: java -jar threads-1.0-SNAPSHOT-tests.jar cscie55.hw4.zoo.animals.AnacondaTest



The structure in src is like below:
[HU-student@localhost src]$ tree
.
├── main
│   └── java
│       └── cscie55
│           └── hw4
│               ├── bank
│               │   ├── AccountImpl.java
│               │   ├── Account.java
│               │   ├── BankImpl.java
│               │   ├── Bank.java
│               │   ├── DuplicateAccountException.java
│               │   └── InsufficientFundsException.java
│               ├── utils
│               │   └── NumUtil.java
│               └── zoo
│                   ├── animals
│                   │   ├── Anaconda.java
│                   │   ├── Animal.java
│                   │   ├── Flamingo.java
│                   │   ├── Fox.java
│                   │   ├── Frog.java
│                   │   └── Giraffe.java
│                   └── iface
│                       ├── Breathable.java
│                       ├── ChewBehavior.java
│                       ├── Domesticable.java
│                       ├── Eatable.java
│                       ├── EatingBehavior.java
│                       ├── Flyable.java
│                       ├── Holdable.java
│                       ├── Huggable.java
│                       ├── Jumpable.java
│                       ├── Learnable.java
│                       ├── Movable.java
│                       ├── Moveable.java
│                       ├── Playable.java
│                       ├── Sleepable.java
│                       ├── SocialBehavior.java
│                       ├── Speakable.java
│                       ├── Swimmable.java
│                       ├── TearBehavior.java
│                       ├── TemperatureRegulatable.java
│                       └── Walkable.java
└── test
    └── java
        └── cscie55
            └── hw4
                ├── bank
                │   └── BankTester.java
                └── zoo
                    └── animals
                        └── AnacondaTester.java


