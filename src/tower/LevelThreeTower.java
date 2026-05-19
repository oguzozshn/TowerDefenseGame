package tower;

import Model.Base.Tower;
import ui.StdDraw;

public class LevelThreeTower extends Tower {
    private double towerX;
    private double towerY;

    public LevelThreeTower(double x, double y) {
        this.towerX = x;
        this.towerY = y;
        this.level = 3;
        this.buildCost = 100;
        this.upgradeCost = 0; // Son seviye, geliştirme yok
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
        StdDraw.picture(towerX, towerY, "Assets/level_three_tower.png", 0.15, 0.15);
    }
}