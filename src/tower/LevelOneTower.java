package tower;

import Model.Base.Tower;
import ui.StdDraw;

public class LevelOneTower extends Tower {
    private double towerX;
    private double towerY;
    private int buildCost;

    public LevelOneTower(double x, double y){
        this.towerX = x;
        this.towerY = y;
        this.level = 1;
        this.buildCost = 100;
        this.upgradeCost = 300;
    }

    @Override
    public void upgrade() {
        // Level 1 -> Level 2 geçişi TowerManager'dan yapılacak
    }

    @Override
    public int getBuildCost() {
        return buildCost;
    }

    @Override
    public int getUpgradeCost() {
        return upgradeCost;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void Draw() {
        StdDraw.picture(towerX, towerY, "Assets/level_one_tower.png", 0.15, 0.15);
    }
}
