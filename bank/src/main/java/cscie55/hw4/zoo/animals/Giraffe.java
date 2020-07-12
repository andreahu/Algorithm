package cscie55.hw4.zoo.animals;

import cscie55.hw4.utils.NumUtil;

import java.util.Arrays;

public class Giraffe extends Animal{

    public Giraffe() { // when empty constructor called
        this(NumUtil.getRandomBetween(1,100)); // fill in first param and pass it to the 2nd constructor
    }

    public Giraffe(int age){ // when 2nd constructor is called, age only is given.
        this(age, "AnnonyConda"); // fill in default name and pass to 3rd constructor
    }

    public Giraffe(int age, String name) { // when 3rd constructor, use params 1 and 2
        this(age, name, new String[]{"grass"}); // and add String[] food and call 4th constructor

    }

    // NOTE: Only NOW do I call super
    public Giraffe(int age, String name, String[] favoriteFoods) { // The ultimate constructor. Pass it ALL to super()
        super(age, name, favoriteFoods);
    }
}