package Model.Base;

public abstract class Tower implements IUpgradable {
    protected double x;
    protected double y;
    protected double range;
    protected int damage;
    protected int level;

    public Tower(){}
    public Tower(double x, double y, double range, int damage, int level) {};

    public void Update(){}
    public void Draw(){}
}
