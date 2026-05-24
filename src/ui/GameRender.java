package ui;

import Model.Base.Tower;
import Model.Base.Enemy;
import tower.*;
import buttons.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GameRender class handles the rendering of the game elements on the screen.
 */
public class GameRender {
    int canvas_width = 800;
    int canvas_height = 600;
    private HudBar hudBar;
    private GameOverScreen gameOverScreen;
    private List<Tower> builtTowers;
    private List<Enemy> enemies;
    private List<double[]> pathWaypoints;
    private List<double[]> towerSlots;
    private List<BuildButton> buildButtons;
    private UpgradeButton[] upgradeButtons;
    private boolean[] slotBuilt;
    private boolean isGameOver = false;

    /**
     * Constructor for GameRender.
     * @param builtTowers
     * @param enemies
     * @param hudBar
     * @param pathWaypoints
     * @param towerSlots
     * @param slotBuilt
     * @param buildButtons
     * @param upgradeButtons
     */
    public GameRender(List<Tower> builtTowers, List<Enemy> enemies, HudBar hudBar,
                      List<double[]> pathWaypoints, List<double[]> towerSlots,
                      boolean[] slotBuilt, List<BuildButton> buildButtons, UpgradeButton[] upgradeButtons) {
        StdDraw.setCanvasSize(canvas_width, canvas_height);
        StdDraw.enableDoubleBuffering();

        this.builtTowers = builtTowers;
        this.enemies = enemies;
        this.hudBar = hudBar;
        this.pathWaypoints = pathWaypoints;
        this.towerSlots = towerSlots;
        this.slotBuilt = slotBuilt;
        this.buildButtons = buildButtons;
        this.upgradeButtons = upgradeButtons;
        this.gameOverScreen = new GameOverScreen(this.hudBar);
    }

    /**
     * Renders the game elements on the screen.
     */
    public void render() {
        StdDraw.picture(0.5, 0.5, "Assets/BackPlan.png", 1.0, 1.0);

        if (isGameOver) {
            GameOverScreen gameOverScreen = new GameOverScreen(this.hudBar);
            gameOverScreen.Draw();
            return;
        }

        for (int i = 0; i < towerSlots.size(); i++) {
            Tower tower = builtTowers.get(i);

            if (tower != null) {
                tower.Draw();
                tower.drawLaser();

                if (tower.getLevel() < 3 && upgradeButtons[i] != null) {
                    upgradeButtons[i].draw();
                }
            } else {
                buildButtons.get(i).draw();
            }
        }
        for (Enemy e : enemies) {
            e.Draw();
        }

        hudBar.draw();
        StdDraw.show();
    }

    /**
     * Sets the game over flag.
     * @param gameOver
     */
    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

}