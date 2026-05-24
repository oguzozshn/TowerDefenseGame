package enemy;

import Model.Base.Enemy;

/**
 * Represents a Vampyr enemy in the game.
 */
public class Vampyr extends Enemy {
    public Vampyr(double x, double y) {
        super(x, y, 0.003, "Assets/vampyr.png", 120, 45, 30);
    }
}