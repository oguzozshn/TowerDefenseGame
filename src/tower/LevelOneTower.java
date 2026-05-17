package tower;

import Model.Base.Tower;

public class LevelOneTower extends Tower {
    public LevelOneTower(double x, double y){};

    @Override
    public void upgrade() {

    }

    @Override
    public int getUpgradeCost() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
