package core;

import Model.MainBase;
import ui.GameRender;
import ui.HudBar;
import Model.Base.Tower;
import Model.Base.Enemy;
import tower.*;
import enemy.*;
import buttons.UpgradeButton;
import java.util.ArrayList;
import java.util.List;
import Model.Base.Button;

/**
 * Manages the simulation aspects of the game, including updating game state and rendering.
 */
public class SimulationManager {
    private final GameRender gameRender;
    private final HudBar hudBar;
    private final MainBase mainBase;
    private final List<Tower> builtTowers;
    private final List<Enemy> enemies;
    private final List<double[]> towerSlots;
    private final List<double[]> pathWaypoints;
    private final List<Button> buttons;
    private int spawnCooldown = 0;
    private boolean isGameOver = false;
    private boolean wasMousePressed = false;
    private final long startTime = System.currentTimeMillis();
    private final int startGold = 100;
    private int score = 0;
    private final int INITIAL_SPAWN_INTERVAL = 45;
    private final int MIN_SPAWN_INTERVAL = 5;

    /**
     * Initializes the simulation manager with default values and sets up the initial game state.
     */
    public SimulationManager() {
        this.mainBase = new MainBase();
        this.hudBar = new HudBar(startGold, mainBase.getHealth(), score);
        this.builtTowers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.towerSlots = List.of(
                new double[]{0.3216, 0.825},
                new double[]{0.6982, 0.800},
                new double[]{0.5091, 0.58},
                new double[]{0.2763, 0.42},
                new double[]{0.6429, 0.39}
        );
        this.pathWaypoints = List.of(
                new double[]{0.0, 0.55},
                new double[]{0.30, 0.55},
                new double[]{0.37, 0.48},
                new double[]{0.45, 0.65},
                new double[]{0.50, 0.73},
                new double[]{0.55, 0.65},
                new double[]{0.65, 0.48},
                new double[]{0.86, 0.48}
        );

        this.buttons = new ArrayList<>();

        for (double[] slot : towerSlots) {
            buttons.add(new buttons.BuildButton(slot[0], slot[1] - 0.06, 0.06, 0.03, 100));
            builtTowers.add(null);
        }

        this.gameRender = new GameRender(builtTowers, enemies, hudBar, towerSlots, buttons);
    }

    /**
     * Updates the simulation state, handles user input, and manages enemy spawning, tower updates, and game rendering.
     */
    public void update() {
        if (isGameOver) {
            gameRender.setGameOver(true);
            return;
        }

        int currentSeconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
        hudBar.setElapsedSeconds(currentSeconds);

        handleMouseInput();
        spawnEnemies();
        updateEnemies();
        updateTowers();

        gameRender.setGameOver(isGameOver);
    }

    /**
     * Manages enemy generation waves over time.
     */
    private void spawnEnemies() {
        long timeElapsed = System.currentTimeMillis() - startTime;
        int currentSpawnInterval = Math.max(MIN_SPAWN_INTERVAL, INITIAL_SPAWN_INTERVAL - (int)(timeElapsed / 10000) * 4);
        spawnCooldown++;

        if (spawnCooldown >= currentSpawnInterval) {
            double startX = pathWaypoints.get(0)[0];
            double startY = pathWaypoints.get(0)[1];
            double random = Math.random();

            if (random > 0.8 && timeElapsed > 60000)
                enemies.add(new Vampyr(startX, startY));
            else if (random > 0.5 && timeElapsed > 30000)
                enemies.add(new Golem(startX, startY));
            else
                enemies.add(new Goblin(startX, startY));

            spawnCooldown = 0;
        }
    }

    /**
     * Updates positions, health checks, and rewards for all active enemies.
     */
    private void updateEnemies() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            e.update(pathWaypoints);

            if (e.isDead()) {
                hudBar.setGold(hudBar.getGold() + e.getGoldReward());
                enemies.remove(i);
                score += e.getScore();
                hudBar.setScore(score);
                continue;
            }

            if (e.isReachedCastle()) {
                mainBase.takeDamage(e.getBaseDamage());
                hudBar.setBaseHealth(mainBase.getHealth());
                enemies.remove(i);

                if (mainBase.isDestroyed()) isGameOver = true;
            }
        }
    }

    /**
     * Signals all built towers to look for targets and attack.
     */
    private void updateTowers() {
        for (Tower tower : builtTowers) {
            if (tower != null) {
                tower.update(enemies);
            }
        }
    }

    /**
     * Handles user mouse input for tower placement and upgrades.
     */
    private void handleMouseInput() {
        boolean isMousePressed = ui.StdDraw.isMousePressed();

        if (isMousePressed && !wasMousePressed) {
            double mouseX = ui.StdDraw.mouseX();
            double mouseY = ui.StdDraw.mouseY();
            int currentGold = hudBar.getGold();

            for (int i = 0; i < towerSlots.size(); i++) {
                double[] slot = towerSlots.get(i);
                Tower currentTower = builtTowers.get(i);
                Model.Base.Button currentButton = buttons.get(i);

                if (currentTower == null) {
                    if (currentButton != null && currentButton.isClicked(mouseX, mouseY) && currentGold >= 100) {
                            hudBar.setGold(currentGold - 100);
                            Tower newTower = new LevelOneTower(slot[0], slot[1]);
                            builtTowers.set(i, newTower);
                            buttons.set(i, new UpgradeButton(slot[0], slot[1] - 0.06, 0.06, 0.03, newTower.getUpgradeCost()));
                            break;
                        }
                    }
                else if (currentButton != null && currentTower instanceof Model.Base.IUpgradable upgradableTower) {

                    if (upgradableTower.getLevel() < 3 && currentButton.isClicked(mouseX, mouseY)) {
                        int cost = upgradableTower.getUpgradeCost();

                            if (currentGold >= cost) {
                                hudBar.setGold(currentGold - cost);

                                Tower upgradedTower = upgradableTower.upgrade();
                                builtTowers.set(i, upgradedTower);

                                if (upgradedTower.getLevel() == 3) {
                                    buttons.set(i, null);
                                } else {
                                    buttons.set(i, new UpgradeButton(slot[0], slot[1] - 0.06, 0.06, 0.03, upgradedTower.getUpgradeCost()));
                                }
                            }
                        break;
                    }
                }
            }
        }
        wasMousePressed = isMousePressed;
    }

    /**
     * Renders the game state, including towers, enemies, and UI elements.
     */
    public void render() {
        gameRender.render();
    }

    /**
     * Returns the hudBar of the game.
     * @return HudBar
     */
    public HudBar getHudBar() { return hudBar; }
}