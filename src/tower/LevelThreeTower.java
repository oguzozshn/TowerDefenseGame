package tower;

import Model.Base.Tower;
import ui.StdDraw;

public class LevelThreeTower extends Tower {
    public LevelThreeTower(double x, double y) {
        this.towerX = x;
        this.towerY = y;
        this.level = 3;
        this.buildCost = 100;
        this.upgradeCost = 0;

        // Seviye 3 İstatistikleri (Muazzam Güç)
        this.damage = 65;
        this.range = 320;
        this.fireRate = 8;
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