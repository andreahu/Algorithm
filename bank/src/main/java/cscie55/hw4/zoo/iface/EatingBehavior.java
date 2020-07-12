package cscie55.hw4.zoo.iface;

import java.util.ArrayList;

public interface EatingBehavior {

    /**
     * Function to get list of foods this animal can eat
     *
     * @return List of foods this animal can eat
     */
    ArrayList<String> canEat();

    /**
     * Function that returns if the animal is vegetarian
     *
     * @return true if vegetarian false otherwise
     */
    boolean isVegetarian();

    /**
     * Checks if the food is in the list of food the animal can eat
     *
     * @param food The food item to check
     * @return true if in the list false otherwise
     */
    boolean isInEdibleFoodList(String food);

    /**
     * The eating behavior
     *
     * @return The eating behavior as a string
     */
    String eat();

    /**
     * Adds the food to the list of favorite foods
     *
     * @param food food item to add to favorite foods list
     */
    void addFavoriteFood(String food);
}
