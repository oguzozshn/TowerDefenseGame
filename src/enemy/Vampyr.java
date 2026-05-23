package enemy;

import Model.Base.Enemy;

public class Vampyr extends Enemy {
    public Vampyr(double x, double y) {
        // En sondaki '3', kaleye ulaştığında 3 can götüreceği anlamına gelir
        super(x, y, 0.003, "Assets/vampyr.png", 120, 45, 30);
    }
}