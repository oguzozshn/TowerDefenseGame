package Model.Base;

import ui.StdDraw;
import java.util.List;

/**
 * Abstract base class for tower entities in the game.
 */
public abstract class Tower {
    protected double towerX;
    protected double towerY;
    protected int level;
    protected int buildCost;
    protected int upgradeCost;
    protected int damage;
    protected int range;
    protected int fireRate;
    protected int attackCooldown = 0;
    private boolean isShooting = false;
    private double lastTargetX;
    private double lastTargetY;

    /**
     * Draws the tower on the screen.
     */
    public abstract void Draw();

    /**
     * Returns the cost of upgrading the tower.
     * @return
     */
    public abstract int getUpgradeCost();

    /**
     * Returns the current level of the tower.
     * @return
     */
    public abstract int getLevel();

    /**
     * Updates the tower's state and behavior based on the list of enemies.
     * @param enemies List of enemies in the game
     */
    public void update(List<Enemy> enemies) {
        this.isShooting = false;

        if (attackCooldown > 0) {
            attackCooldown--;
        }

        if (attackCooldown == 0) {
            Enemy target = findTarget(enemies);
            if (target != null) {
                target.takeDamage(damage);

                this.lastTargetX = target.getX();
                this.lastTargetY = target.getY();
                this.isShooting = true;

                attackCooldown = fireRate;
            }
        }
    }

    /**
     * Finds the nearest enemy within the tower's range.
     * @param enemies List of enemies in the game
     * @return The nearest enemy within range, or null if no enemy is found
     */
    private Enemy findTarget(List<Enemy> enemies) {
        double rangeRadius = this.range / 1000.0;

        for (Enemy e : enemies) {
            if (e.isDead() || e.isReachedCastle()) continue;

            double distance = Math.sqrt(Math.pow(e.getX() - towerX, 2) + Math.pow(e.getY() - towerY, 2));
            if (distance <= rangeRadius) {
                return e;
            }
        }
        return null;
    }

    /**
     * Draws the laser effect from the tower to the target enemy.
     */
    public void drawLaser() {
        if (isShooting) {
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(towerX, towerY, lastTargetX, lastTargetY);
            StdDraw.setPenRadius();
        }
    }
}