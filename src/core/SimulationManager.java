package core;

import ui.GameRender;
import ui.HudBar;
import Model.Base.Tower;
import Model.Base.Enemy;
import tower.*;
import enemy.*;
import buttons.BuildButton;
import buttons.UpgradeButton;
import java.util.ArrayList;
import java.util.List;

public class SimulationManager {
    private GameRender gameRender;
    private HudBar hudBar;

    private List<Tower> builtTowers;
    private List<Enemy> enemies;
    private List<double[]> towerSlots;
    private List<double[]> pathWaypoints;
    private List<BuildButton> buildButtons;
    private UpgradeButton[] upgradeButtons;
    private boolean[] slotBuilt;

    private int spawnCooldown = 0;
    private boolean isGameOver = false;
    private boolean wasMousePressed = false;

    public SimulationManager() {
        this.hudBar = new HudBar(1000, 5);
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
                new double[]{0.0, 0.48},
                new double[]{0.35, 0.48},
                new double[]{0.50, 0.73},
                new double[]{0.65, 0.48},
                new double[]{0.86, 0.48}
        );

        this.slotBuilt = new boolean[towerSlots.size()];
        this.upgradeButtons = new UpgradeButton[towerSlots.size()];
        this.buildButtons = new ArrayList<>();

        for (double[] slot : towerSlots) {
            buildButtons.add(new BuildButton(slot[0], slot[1] - 0.06, 0.06, 0.04));
            builtTowers.add(null);
        }

        // GameRender'ı initialize et (buton referanslarıyla birlikte)
        this.gameRender = new GameRender(builtTowers, enemies, hudBar,
                pathWaypoints, towerSlots, slotBuilt, buildButtons, upgradeButtons);
    }

    // Oyun mantığını güncelle
    // Oyun mantığını güncelle
    public void update() {
        // YENİ KONTROL: Eğer oyun bittiyse ne tıklamaları al ne de düşmanları yürüt
        if (isGameOver) {
            gameRender.setGameOver(true);
            return;
        }

        // 🌟 KRİTİK EKSİK BURASIYDI: Tıklama kontrolünü her karede tetikliyoruz
        handleMouseInput();

        // Düşman doğma
        spawnCooldown++;
        if (spawnCooldown >= 45) {
            double startX = pathWaypoints.get(0)[0];
            double startY = pathWaypoints.get(0)[1];
            if (Math.random() < 0.7) {
                enemies.add(new Goblin(startX, startY));
            } else {
                enemies.add(new Golem(startX, startY));
            }
            spawnCooldown = 0;
        }

        // Düşmanları güncelle
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            e.update(pathWaypoints);

            if (e.isDead()) {
                hudBar.setGold(hudBar.getGold() + e.getGoldReward());
                enemies.remove(i);
                continue;
            }

            if (e.isReachedCastle()) {
                int remainingHealth = hudBar.getBaseHealth() - e.getBaseDamage();
                hudBar.setBaseHealth(remainingHealth);
                enemies.remove(i);

                System.out.println("Kale hasar aldı! Kalan Can: " + remainingHealth);

                if (remainingHealth <= 0) {
                    isGameOver = true;
                }
                continue;
            }
        }

        // Kulelerin saldırısını güncelle
        for (int i = 0; i < towerSlots.size(); i++) {
            if (slotBuilt[i] && builtTowers.get(i) != null) {
                builtTowers.get(i).updateAttack(enemies);
            }
        }

        gameRender.setGameOver(isGameOver);
    }

    // Mouse input işleme
    private void handleMouseInput() {
        boolean isMousePressed = ui.StdDraw.isMousePressed();

        if (isMousePressed && !wasMousePressed) {
            double mouseX = ui.StdDraw.mouseX();
            double mouseY = ui.StdDraw.mouseY();
            int currentGold = hudBar.getGold();

            for (int i = 0; i < towerSlots.size(); i++) {
                double[] slot = towerSlots.get(i);

                if (!slotBuilt[i]) {
                    if (buildButtons.get(i).isClicked(mouseX, mouseY)) {
                        if (currentGold >= 100) {
                            hudBar.setGold(currentGold - 100);
                            slotBuilt[i] = true;
                            builtTowers.set(i, new LevelOneTower(slot[0], slot[1]));
                            upgradeButtons[i] = new UpgradeButton(slot[0], slot[1] - 0.06, 0.06, 0.03, 300);
                        }
                        break;
                    }
                } else {
                    Tower currentTower = builtTowers.get(i);
                    if (currentTower != null && currentTower.getLevel() < 3 && upgradeButtons[i] != null) {
                        if (upgradeButtons[i].isClicked(mouseX, mouseY)) {
                            int cost = currentTower.getUpgradeCost();

                            if (currentGold >= cost) {
                                hudBar.setGold(currentGold - cost);

                                if (currentTower.getLevel() == 1) {
                                    builtTowers.set(i, new LevelTwoTower(slot[0], slot[1]));
                                    upgradeButtons[i] = new UpgradeButton(slot[0], slot[1] - 0.06, 0.06, 0.03, 500);
                                } else if (currentTower.getLevel() == 2) {
                                    builtTowers.set(i, new LevelThreeTower(slot[0], slot[1]));
                                    upgradeButtons[i] = null;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        wasMousePressed = isMousePressed;
    }

    // Render sadece görsel işler yapar
    public void render() {
        gameRender.render();
    }

    public List<Tower> getBuiltTowers() { return builtTowers; }
    public List<Enemy> getEnemies() { return enemies; }
    public HudBar getHudBar() { return hudBar; }
}