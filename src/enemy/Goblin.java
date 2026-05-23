package enemy;

import Model.Base.Enemy;

public class Goblin extends Enemy {
    public Goblin(double x, double y) {
        // En sondaki '1', kaleye ulaştığında 1 can götüreceği anlamına gelir
        super(x, y, 0.008, "Assets/goblin.png", 20, 15, 10);
    }
}