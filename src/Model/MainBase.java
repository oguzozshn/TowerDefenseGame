package Model;

public class MainBase {
    private int health;
    private int level;
    private int upgradeCost;

    public MainBase() {};
    public MainBase(int health, int level, int upgradeCost) {};

    public void takeDamage(int damage){};
    public boolean isDestroyed(){return false;};

}
