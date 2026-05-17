package Model.Base;

public abstract class Enemy {
    protected double x;
    protected double y;
    protected double speed;
    protected int health;
    protected int rewardGold;

    public Enemy(){};
    public Enemy(double x, double y, double speed, int health, int rewardGold){};

    public void move(){};
    public void draw(){};
    public void takeDamage(){};
    public void isDead(){};

}
