package tower;

import Model.Base.Tower;
import ui.StdDraw;

public class LevelTwoTower extends Tower {
    private double towerX;
    private double towerY;

    public LevelTwoTower(double x, double y) {
        this.towerX = x;
        this.towerY = y;
        this.level = 2;
        this.buildCost = 100;
        this.upgradeCost = 500; // Level 3'e geçiş ücreti
    }

    @Override
    public void upgrade() {}

    @Override
    public int getBuildCost() { return buildCost; }

    @Override
    public int getUpgradeCost() { return upgradeCost; }

    @Override
    public int getLevel() { return level; }

    @Override
    public void Draw() {
        StdDraw.picture(towerX, towerY, "Assets/level_two_tower.png", 0.15, 0.15);
    }
}