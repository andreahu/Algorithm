package cscie55.hw4.zoo.iface;

/**
 * Interface for Chewing Behavior
 */
public interface ChewBehavior {
    /**
     * Displays how the animal eats by chewing
     *
     * @param food The food to be chewed on
     * @return The value how animal chews
     */
    String chew(String food);
}
