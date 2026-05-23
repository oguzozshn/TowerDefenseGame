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

    // YENİ SAVAŞ DEĞİŞKENLERİ
    protected int hp;
    protected int maxHp;
    protected int goldReward;
    protected int baseDamage;

    public Enemy(double x, double y, double speed, String texturePath, int hp, int goldReward, int baseDamage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texturePath = texturePath;
        this.hp = hp;
        this.maxHp = hp;
        this.goldReward = goldReward;
        this.baseDamage = 10;
    }

    public void update(List<double[]> waypoints) {
        if (reachedCastle || isDead() || currentWaypointIndex >= waypoints.size()) {
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
        if (isDead()) return;

        // Düşman görseli
        StdDraw.picture(x, y, texturePath, 0.08, 0.08);

        // YENİ: Küçük bir Can Barı çizimi (Görsel geri bildirim için)
        double barWidth = 0.05;
        double barHeight = 0.008;
        double hpRatio = (double) hp / maxHp;

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(x, y + 0.05, barWidth / 2, barHeight / 2);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(x - (barWidth * (1 - hpRatio)) / 2, y + 0.05, (barWidth * hpRatio) / 2, barHeight / 2);
    }

    // Savaş mekaniği yardımcı metotları
    public void takeDamage(int amount) {
        this.hp -= amount;
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public int getBaseDamage() { return baseDamage; }
    public double getX() { return x; }
    public double getY() { return y; }
    public int getGoldReward() { return goldReward; }
    public boolean isReachedCastle() { return reachedCastle; }
}