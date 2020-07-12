package cscie55.hw4.zoo.animals;

import cscie55.hw4.utils.NumUtil;

import java.util.Arrays;

public class Fox extends Animal {

    public Fox() { // when empty constructor called
        this(NumUtil.getRandomBetween(1,100)); // fill in first param and pass it to the 2nd constructor
    }

    public Fox(int age){ // when 2nd constructor is called, age only is given.
        this(age, "AnnonyConda"); // fill in default name and pass to 3rd constructor
    }

    public Fox(int age, String name) { // when 3rd constructor, use params 1 and 2
        this(age, name, new String[]{"rabbit"}); // and add String[] food and call 4th constructor

    }

    // NOTE: Only NOW do I call super
    public Fox(int age, String name, String[] favoriteFoods) { // The ultimate constructor. Pass it ALL to super()
        super(age, name, favoriteFoods);
    }


    public String play() { return "Abay-ba-da bum-bum bay-do"; }

    public String speak() {
        final String[] FOX_SOUNDS = {
                "Ring-ding-ding-ding-dingeringeding!",
                "Gering-ding-ding-ding-dingeringeding!",
                "Wa-pa-pa-pa-pa-pa-pow",
                "Hatee-hatee-hatee-ho",
                "Joff-tchoff-tchoffo-tchoffo-tchoff!",
                "Tchoff-tchoff-tchoffo-tchoffo-tchoff!",
                "Jacha-chacha-chacha-chow!",
                "Chacha-chacha-chacha-chow!",
                "Fraka-kaka-kaka-kaka-kow!",
                "A-hee-ahee ha-hee!",
                "A-oo-oo-oo-ooo!",
                "Woo-oo-oo-ooo!",
                "Wa-wa-way-do, wub-wid-bid-dum-way-do, wa-wa-way-do",
                "Mama-dum-day-do",
                "Bay-budabud-dum-bam"};

        // Generate random number to select sound to produce
        int soundIndex = ((int) Math.random()) % FOX_SOUNDS.length;

        return FOX_SOUNDS[soundIndex];
    }
}