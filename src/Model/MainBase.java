package Model;

public class MainBase {
    private int health = 50;

    // 🌟 DÜZELTME: SimulationManager'da "new MainBase()" dendiği için parametresiz constructor ekledik
    public MainBase() {
        this.health = 50; // Varsayılan başlangıç canı
    }

    public MainBase(int health) {
        this.health = health;
    }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    // 🌟 GERÇEKLENEN KISIM: Kale hasar aldığında canı gerçekten düşürelim
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    // 🌟 GERÇEKLENEN KISIM: Can sıfıra veya altına indiyse yıkıldı (true) dön
    public boolean isDestroyed() {
        return this.health <= 0;
    }
}