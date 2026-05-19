package Model.Base;

import ui.StdDraw;
import java.util.List;

public abstract class Enemy {
    protected double x;
    protected double y;
    protected double speed;
    protected int currentWaypointIndex = 0;
    protected boolean reachedCastle = false;
    protected String texturePath;
    protected int health;

    public Enemy(double x, double y, double speed, String texturePath, int health) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texturePath = texturePath;
        this.health = health;
    }

    public void update(List<double[]> waypoints) {
        if (reachedCastle || currentWaypointIndex >= waypoints.size()) {
            return;
        }

        double[] target = waypoints.get(currentWaypointIndex);
        double targetX = target[0];
        double targetY = target[1];

        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < speed) {
            x = targetX;
            y = targetY;
            currentWaypointIndex++;

            if (currentWaypointIndex >= waypoints.size()) {
                reachedCastle = true;
            }
        } else {
            x += (dx / distance) * speed;
            y += (dy / distance) * speed;
        }
    }

    public void Draw() {
        // Her düşman kendi texturePath'indeki görseli çizer
        StdDraw.picture(x, y, texturePath, 0.08, 0.08);
    }

    public int getHealth() { return health; }

    public boolean isReachedCastle() { return reachedCastle; }
}