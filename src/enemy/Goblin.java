package enemy;

import Model.Base.Enemy;

public class Goblin extends Enemy {
    public Goblin(double x, double y) {
        // x, y, speed (0.008 - Hızlı), Görsel Bölümü
        super(x, y, 0.008, "Assets/goblin.png", 10);
    }
}