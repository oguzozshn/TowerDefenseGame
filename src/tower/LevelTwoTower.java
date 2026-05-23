package tower;

import Model.Base.Tower;
import ui.StdDraw;

public class LevelTwoTower extends Tower {
    public LevelTwoTower(double x, double y) {
        this.towerX = x;
        this.towerY = y;
        this.level = 2;
        this.buildCost = 100;
        this.upgradeCost = 500;

        // Seviye 2 İstatistikleri
        this.damage = 25;
        this.range = 260;    // Menzil arttı
        this.fireRate = 12;  // Daha hızlı ateş eder
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