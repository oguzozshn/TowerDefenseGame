package tower;

import Model.Base.Tower;
import ui.StdDraw;

/**
 * Represents a level three tower in the game.
 */
public class LevelThreeTower extends Tower {
    public LevelThreeTower(double x, double y) {
        this.towerX = x;
        this.towerY = y;
        this.level = 3;
        this.damage = 30;
        this.range = 350;
        this.fireRate = 15;
        this.upgradeCost = 0;
    }

    /**
     * Upgrades the tower to level four.
     * @return The upgraded tower.
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
     * Draws the level three tower on the game screen.
     */
    @Override
    public void Draw() {
        StdDraw.picture(towerX, towerY, "Assets/level_three_tower.png", 0.15, 0.15);
    }
}