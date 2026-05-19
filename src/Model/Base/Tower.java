package Model.Base;

public abstract class Tower implements IUpgradable {
    protected double towerX;
    protected double towerY;
    protected int level;
    protected int buildCost;
    protected int upgradeCost;
    protected int damage;
    protected int range;
    protected int fireRate;

    public abstract void Draw();
    public abstract int getBuildCost();
    public abstract int getUpgradeCost();
    public abstract int getLevel();
    
    public double getTowerX() {
        return towerX;
    }

    public double getTowerY() {
        return towerY;
    }

    public boolean isClicked(double mouseX, double mouseY, double radius) {
        double distance = Math.sqrt(Math.pow(mouseX - towerX, 2) + Math.pow(mouseY - towerY, 2));
        return distance < radius;
    }
}
