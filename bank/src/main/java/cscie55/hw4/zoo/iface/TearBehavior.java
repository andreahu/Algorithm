package cscie55.hw4.zoo.iface;

/**
 * Interface for Tear Behavior
 */
public interface TearBehavior {
    /**
     * Displays how animal eats by tearing
     * @param food Food input
     * @return How the animal tears
     */
    String tear(String food);
}
