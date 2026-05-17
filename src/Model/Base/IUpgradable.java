package Model.Base;

public interface IUpgradable {
    void upgrade();
    int getUpgradeCost();
    int getLevel();
}
