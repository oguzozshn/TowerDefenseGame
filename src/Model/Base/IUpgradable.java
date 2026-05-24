package Model.Base;

/**
 * Interface for upgradable towers.
 */
public interface IUpgradable {
    Tower upgrade();
    int getUpgradeCost();
    int getLevel();
}
