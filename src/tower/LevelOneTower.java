package tower;

import Model.Base.IUpgradable;
import Model.Base.Tower;
import ui.StdDraw;

/**
 * Represents a level one tower in the game.
 */
public class LevelOneTower extends Tower implements IUpgradable {
    public LevelOneTower(double x, double y){
        this.towerX = x;
        this.towerY = y;
        this.level = 1;
        this.buildCost = 100;
        this.upgradeCost = 300;

        this.damage = 10;
        this.range = 220;
        this.fireRate = 20;
    }

    /**
     * Upgrades the tower to level two.
     * @return The upgraded tower.
     */
    @Override
    public Tower upgrade() {
        return new LevelTwoTower(this.towerX, this.towerY);
    }

    /**
     * Returns the cost of upgrading the tower to level two.
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
        StdDraw.picture(towerX, towerY, "Assets/level_one_tower.png", 0.15, 0.15);
    }
}