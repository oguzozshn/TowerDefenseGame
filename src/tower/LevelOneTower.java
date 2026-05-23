package tower;

import Model.Base.IUpgradable;
import Model.Base.Tower;
import ui.StdDraw;

public class LevelOneTower extends Tower implements IUpgradable {
    public LevelOneTower(double x, double y){
        this.towerX = x;
        this.towerY = y;
        this.level = 1;
        this.buildCost = 100;
        this.upgradeCost = 300;

        // Seviye 1 İstatistikleri
        this.damage = 10;
        this.range = 220;    // 0.22 Yarıçap menzil
        this.fireRate = 20;  // 30 FPS hızda saniyede 2 kez ateş eder
    }

    @Override
    public Tower upgrade() {
        // Kendini yok edip yerine bir adet LevelTwoTower doğuruyor!
        return new LevelTwoTower(this.towerX, this.towerY);
    }

    @Override
    public int getBuildCost() { return buildCost; }
    @Override
    public int getUpgradeCost() { return upgradeCost; }
    @Override
    public int getLevel() { return level; }

    @Override
    public void Draw() {
        StdDraw.picture(towerX, towerY, "Assets/level_one_tower.png", 0.15, 0.15);
    }
}