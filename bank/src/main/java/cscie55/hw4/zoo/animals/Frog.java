package cscie55.hw4.zoo.animals;

import cscie55.hw4.utils.NumUtil;
import cscie55.hw4.zoo.iface.Eatable;
import cscie55.hw4.zoo.iface.Playable;
import cscie55.hw4.zoo.iface.Speakable;

import java.util.*;

/**
 * This is Frog class.

 * @since   2018-09-14
 */
public class Frog extends Animal implements Playable, Speakable {

    public Frog() { // when empty constructor called
        this(NumUtil.getRandomBetween(1,100)); // fill in first param and pass it to the 2nd constructor
    }

    public Frog(int age){ // when 2nd constructor is called, age only is given.
        this(age, "AnnonyConda"); // fill in default name and pass to 3rd constructor
    }

    public Frog(int age, String name) { // when 3rd constructor, use params 1 and 2
        this(age, name, new String[]{"bugs"}); // and add String[] food and call 4th constructor

    }

    // NOTE: Only NOW do I call super
    public Frog(int age, String name, String[] favoriteFoods) { // The ultimate constructor. Pass it ALL to super()
        super(age, name, favoriteFoods);
    }

    /**
     * This method will retrieve the animal sound.
     * @return String return sound of the animal
     */
    @Override
    public String speak()
    {
        return  "croak";
    }

    /**
     * This method will retrieve the animal play sound.
     * @return String return play sound of the animal
     */
    @Override
    public String play()
    {

        return  "croak";
    }
    /**
     * This method will retrieve the one of the favorite food of the animal.
     * @return String return favorite food of the animal
     */


}