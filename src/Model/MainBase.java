package Model;

/**
 * Represents the main base in the game, with health and methods to interact with it.
 */
public class MainBase {
    private int health = 50;

    /**
     * Constructor for MainBase.
     */
    public MainBase() {}

    /**
     * Constructor for MainBase with initial health.
     * @param health Initial health of the main base.
     */
    public MainBase(int health) {
        this.health = health;
    }

    /**
     * Getter and setter methods for health.
     * @return The current health of the main base.
     */
    public int getHealth() { return health; }

    /**
     * Sets the health of the main base.
     * @param health
     */
    public void setHealth(int health) { this.health = health; }

    /**
     * Takes damage from the player.
     * @param damage
     */
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    /**
     * Checks if the main base is destroyed.
     * @return True if health is less than or equal to 0, false otherwise.
     */
    public boolean isDestroyed() {
        return this.health <= 0;
    }
}