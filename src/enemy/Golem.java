package enemy;

import Model.Base.Enemy;

public class Golem extends Enemy {
    public Golem(double x, double y) {
        // x, y, speed (0.003 - Yavaş), Görsel Bölümü
        super(x, y, 0.003, "Assets/golem.png", 50);
    }
}