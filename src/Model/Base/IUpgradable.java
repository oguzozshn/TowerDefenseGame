package Model.Base;

public interface IUpgradable {
    Tower upgrade();
    int getUpgradeCost();
    int getLevel();
}
