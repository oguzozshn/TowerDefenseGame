package enemy;

import Model.Base.Enemy;

/**
 * Represents a Golem enemy in the game.
 */
public class Golem extends Enemy {
    public Golem(double x, double y) {
        // En sondaki '3', kaleye ulaştığında 3 can götüreceği anlamına gelir
        super(x, y, 0.003, "Assets/golem.png", 80, 30, 20);
    }
}