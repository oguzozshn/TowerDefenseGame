package Model.Base;

import ui.StdDraw;
import java.util.List;

/**
 * Base class for all enemies in the game.
 */
public abstract class Enemy {
    protected double x;
    protected double y;
    protected double speed;
    protected int currentWaypointIndex = 0;
    protected boolean reachedCastle = false;
    protected String texturePath;

    protected int hp;
    protected int maxHp;
    protected int goldReward;
    protected int baseDamage;
    protected int score;

    /**
     * Constructs an Enemy object with the specified properties.
     *
     * @param x           The x-coordinate of the enemy's position.
     * @param y           The y-coordinate of the enemy's position.
     * @param speed       The movement speed of the enemy.
     * @param texturePath The file path to the texture/image of the enemy.
     * @param hp          The initial health points of the enemy.
     * @param goldReward  The amount of gold rewarded upon defeating the enemy.
     * @param score       The score granted upon defeating the enemy.
     */
    public Enemy(double x, double y, double speed, String texturePath, int hp, int goldReward, int score) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texturePath = texturePath;
        this.hp = hp;
        this.maxHp = hp;
        this.goldReward = goldReward;
        this.baseDamage = 10;
        this.score = score;
    }

    /**
     * Updates the enemy's position and behavior based on the provided waypoints.
     * @param waypoints The list of waypoints defining the enemy's path.
     */
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

    /**
     * Draws the enemy on the game canvas.
     * Displays the enemy's sprite and health bar.
     */
    public void Draw() {
        if (isDead()) return;

        StdDraw.picture(x, y, texturePath, 0.08, 0.08);

        double barWidth = 0.05;
        double barHeight = 0.008;
        double hpRatio = (double) hp / maxHp;

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(x, y + 0.05, barWidth / 2, barHeight / 2);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle(x - (barWidth * (1 - hpRatio)) / 2, y + 0.05, (barWidth * hpRatio) / 2, barHeight / 2);
    }

    /**
     * Takes damage from the player.
     * @param amount The amount of damage to be taken.
     */
    public void takeDamage(int amount) {
        this.hp -= amount;
    }

    /**
     * Checks if the enemy is dead.
     * @return True if the enemy's health is less than or equal to zero, false otherwise.
     */
    public boolean isDead() {
        return this.hp <= 0;
    }

    /**
     * Getter for base damage.
     * @return The base damage of the enemy.
     */
    public int getBaseDamage() { return baseDamage; }

    /**
     * Getter for enemy's X position.
     * @return The X coordinate of the enemy.
     */
    public double getX() { return x; }

    /**
     * Getter for enemy's Y position.
     * @return The Y coordinate of the enemy.
     */
    public double getY() { return y; }

    /**
     * Getter for gold reward.
     * @return The amount of gold the enemy rewards upon death.
     */
    public int getGoldReward() { return goldReward; }

    /**
     * Checks if the enemy has reached the castle.
     * @return True if the enemy has reached the castle, false otherwise.
     */
    public boolean isReachedCastle() { return reachedCastle; }

    /**
     * Getter for enemy's score.
     * @return The score associated with the enemy.
     */
    public int getScore() { return score; }
}