package ui;

import Model.Base.Tower;
import Model.Base.Enemy;
import Model.Base.Button; // Üst sınıfımız
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
    private List<double[]> towerSlots;
    private final List<Button> buttons;
    private boolean isGameOver = false;

    /**
     * Constructor for GameRender.
     */
    public GameRender(List<Tower> builtTowers, List<Enemy> enemies, HudBar hudBar,
                      List<double[]> towerSlots, List<Button> buttons) {
        StdDraw.setCanvasSize(canvas_width, canvas_height);
        StdDraw.enableDoubleBuffering();

        this.builtTowers = builtTowers;
        this.enemies = enemies;
        this.hudBar = hudBar;
        this.towerSlots = towerSlots;
        this.buttons = buttons;
        this.gameOverScreen = new GameOverScreen(this.hudBar);
    }

    /**
     * Renders the game elements on the screen.
     */
    public void render() {
        StdDraw.picture(0.5, 0.5, "Assets/BackPlan.png", 1.0, 1.0);

        if (isGameOver) {
            this.gameOverScreen.Draw();
            return;
        }

        drawTowers();
        drawEnemies();
        hudBar.draw();
        StdDraw.show();
    }

    /**
     * Sets the game over flag.
     */
    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    /**
     * Draws the enemies on the screen.
     */
    public void drawEnemies(){
        for (Enemy e : enemies) {
            e.Draw();
        }
    }

    /**
     * Draws the towers and their buttons on the screen.
     */
    public void drawTowers(){
        for (int i = 0; i < towerSlots.size(); i++) {
            Tower tower = builtTowers.get(i);

            if (tower != null) {
                tower.Draw();
                tower.drawLaser();
            }

            Button activeButton = buttons.get(i);
            if (activeButton != null) {
                activeButton.draw();
            }
        }
    }
}