package tower;

import Model.Base.IUpgradable;
import Model.Base.Tower;
import ui.StdDraw;

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

    @Override
    public Tower upgrade() {
        // 🌟 DÜZELTİLDİ: Level 2 yükseldiğinde artık bir LevelThreeTower doğurmalı!
        return new LevelThreeTower(this.towerX, this.towerY);
    }

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