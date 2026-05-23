package Model.Base;

import ui.StdDraw;
import java.util.List;

public abstract class Tower {
    protected double towerX;
    protected double towerY;
    protected int level;
    protected int buildCost;
    protected int upgradeCost;

    // Savaş İstatistikleri
    protected int damage;
    protected int range;       // Örn: 250 birim (Ekran ölçeğinde 0.25 yarıçap demektir)
    protected int fireRate;    // Kaç karede (frame) bir ateş edeceği süresi
    protected int attackCooldown = 0; // Kalan bekleme süresi kare sayısı

    // 🌟 YENİ: Render aşamasına bilgi aktarmak için eklenen değişkenler
    private boolean isShooting = false;
    private double lastTargetX;
    private double lastTargetY;

    public abstract void Draw();
    public abstract int getBuildCost();
    public abstract int getUpgradeCost();
    public abstract int getLevel();
    public abstract void upgrade();

    // Ateş etme mantığı ana döngüsü
    public void updateAttack(List<Enemy> enemies) {
        // 🌟 YENİ: Her kare başında ateş etme durumunu sıfırla
        this.isShooting = false;

        if (attackCooldown > 0) {
            attackCooldown--; // Bekleme süresini düşür
        }

        if (attackCooldown == 0) {
            Enemy target = findTarget(enemies);
            if (target != null) {
                target.takeDamage(damage); // Hasar ver

                // 🌟 GÜNCELLENDİ: Anlık çizmek yerine koordinatları hafızaya alıyoruz
                this.lastTargetX = target.getX();
                this.lastTargetY = target.getY();
                this.isShooting = true;

                attackCooldown = fireRate; // Cooldown sıfırla
            }
        }
    }

    // Menzildeki ilk canlı düşmanı bulma algoritması
    private Enemy findTarget(List<Enemy> enemies) {
        double rangeRadius = this.range / 1000.0; // int değeri StdDraw koordinatına çevir (Örn: 250 -> 0.25)

        for (Enemy e : enemies) {
            if (e.isDead() || e.isReachedCastle()) continue;

            double distance = Math.sqrt(Math.pow(e.getX() - towerX, 2) + Math.pow(e.getY() - towerY, 2));
            if (distance <= rangeRadius) {
                return e; // Menzildeki ilk düşmanı hedef seç
            }
        }
        return null;
    }

    // 🌟 GÜNCELLENDİ: Artık public ve parametresiz. GameRender içinden çağrılacak.
    public void drawLaser() {
        if (isShooting) {
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(towerX, towerY, lastTargetX, lastTargetY);
            StdDraw.setPenRadius(); // Kalem kalınlığını normale döndür
        }
    }

    public double getTowerX() { return towerX; }
    public double getTowerY() { return towerY; }
}