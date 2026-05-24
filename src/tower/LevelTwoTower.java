package tower;

import Model.Base.IUpgradable;
import Model.Base.Tower;
import ui.StdDraw;

/**
 * Represents a level two tower in the game.
 */
public class LevelTwoTower extends Tower implements IUpgradable {
    public LevelTwoTower(double x, double y) {
        this.towerX = x;
        this.towerY = y;
        this.level = 2;
        this.damage = 25;
        this.range = 300;
        this.fireRate = 20;
        this.upgradeCost = 500;
    }

    /**
     * Upgrades the tower to level three.
     * @return The upgraded tower.
     */
    @Override
    public Tower upgrade() {
        return new LevelThreeTower(this.towerX, this.towerY);
    }

    /**
     * Returns the cost of upgrading the tower to level three.
     * @return The cost of upgrading the tower.
     */
    @Override
    public int getUpgradeCost() { return upgradeCost; }

    /**
     * Returns the level of the tower.
     * @return The level of the tower.
     */
    @Override
    public int getLevel() { return level; }

    /**
     * Draws the tower on the screen.
     */
    @Override
    public void Draw() {
        StdDraw.picture(towerX, towerY, "Assets/level_two_tower.png", 0.15, 0.15);
    }
}