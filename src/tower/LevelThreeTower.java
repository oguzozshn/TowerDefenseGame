package tower;

import Model.Base.Tower;
import ui.StdDraw;

public class LevelThreeTower extends Tower {
    public LevelThreeTower(double x, double y) {
        this.towerX = x;
        this.towerY = y;
        this.level = 3;       // 🌟 DÜZELTİLDİ: Seviye 3 olmalı
        this.damage = 50;      // 🌟 Tavsiye: Son seviye hasarı daha yüksek olmalı (Örn: 50)
        this.range = 350;      // 🌟 Tavsiye: Menzil biraz daha geniş olmalı
        this.fireRate = 15;    // 🌟 Tavsiye: Daha hızlı ateş etmeli
        this.upgradeCost = 0;  // Son seviye olduğu için upgrade maliyeti kalmadı
    }

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