package ui;

import Model.Base.Tower;
import Model.Base.Enemy;
import tower.*;
import buttons.*;
import java.util.ArrayList;
import java.util.List;

public class GameRender {
    int canvas_width = 800;
    int canvas_height = 600;
    private HudBar hudBar;

    // 🌟 YENİ: GameOver sınıfı için bir referans alanı tanımlıyoruz
    private GameOverScreen gameOverScreen;

    private List<Tower> builtTowers;
    private List<Enemy> enemies;
    private List<double[]> pathWaypoints;
    private List<double[]> towerSlots;
    private List<BuildButton> buildButtons;
    private UpgradeButton[] upgradeButtons;
    private boolean[] slotBuilt;
    private boolean isGameOver = false;

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

        // 🌟 YENİ: GameRender nesnesi oluştuğunda GameOver ekranını da hafızada oluşturuyoruz
        this.gameOverScreen = new GameOverScreen();
    }

    public void render() {
        // Arkaplan (Her karede ekranı temizler)
        StdDraw.picture(0.5, 0.5, "Assets/BackPlan.png", 1.0, 1.0);

        // 🌟 DEĞİŞEN KISIM: Eski private metot yerine yeni sınıfın Draw() metodu çağrılıyor
        if (isGameOver) {
            gameOverScreen.Draw();
            return;
        }

        // Slot butonlarını ve kuleleri çiz
        for (int i = 0; i < towerSlots.size(); i++) {
            if (slotBuilt[i] && builtTowers.get(i) != null) {
                Tower tower = builtTowers.get(i);
                tower.Draw();
                tower.drawLaser(); // Lazer çizimi

                if (tower.getLevel() < 3 && upgradeButtons[i] != null) {
                    upgradeButtons[i].draw();
                }
            } else {
                buildButtons.get(i).draw();
            }
        }

        // Düşmanları çiz
        for (Enemy e : enemies) {
            e.Draw();
        }

        // HUD çiz
        hudBar.draw();
        StdDraw.show();
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    public HudBar getHudBar() {
        return hudBar;
    }
}