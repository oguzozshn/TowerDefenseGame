package core;

import Model.MainBase;
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
    private MainBase mainBase;

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
    private long startTime = System.currentTimeMillis();
    private int startGold = 20000;

    // 🌟 YENİ: Spawn hızı sınırları
    private final int INITIAL_SPAWN_INTERVAL = 45; // Oyun başındaki doğma süresi (kare)
    private final int MIN_SPAWN_INTERVAL = 5;     // Düşmanların gelebileceği maksimum çılgın hız sınırı

    public SimulationManager() {
        this.mainBase = new MainBase();
        this.hudBar = new HudBar(startGold, mainBase.getHealth());
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

        this.slotBuilt = new boolean[towerSlots.size()];
        this.upgradeButtons = new UpgradeButton[towerSlots.size()];
        this.buildButtons = new ArrayList<>();

        for (double[] slot : towerSlots) {
            buildButtons.add(new BuildButton(slot[0], slot[1] - 0.06, 0.06, 0.04));
            builtTowers.add(null);
        }

        this.gameRender = new GameRender(builtTowers, enemies, hudBar,
                pathWaypoints, towerSlots, slotBuilt, buildButtons, upgradeButtons);
    }

    // 🌟 YENİ VE TEMİZ UPDATE: Kule döngüsündeki slotBuilt kalabalığı gitti
    public void update() {
        if (isGameOver) {
            gameRender.setGameOver(true);
            return;
        }

        handleMouseInput();

        // Düşman Doğma Mekanizması (Dinamik)
        long timeElapsed = System.currentTimeMillis() - startTime;
        int currentSpawnInterval = Math.max(MIN_SPAWN_INTERVAL, INITIAL_SPAWN_INTERVAL - (int)(timeElapsed / 10000) * 4);
        spawnCooldown++;

        if (spawnCooldown >= currentSpawnInterval) {
            double startX = pathWaypoints.get(0)[0];
            double startY = pathWaypoints.get(0)[1];
            double random = Math.random();

            if (random > 0.8 && timeElapsed > 60000)      enemies.add(new Vampyr(startX, startY));
            else if (random > 0.5 && timeElapsed > 30000) enemies.add(new Golem(startX, startY));
            else                                          enemies.add(new Goblin(startX, startY));

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
                mainBase.takeDamage(e.getBaseDamage());
                hudBar.setBaseHealth(mainBase.getHealth());
                enemies.remove(i);

                if (mainBase.isDestroyed()) isGameOver = true;
                continue;
            }
        }

        // 🌟 Kulelerin saldırısını güncelle (Sadece kule varsa update et diyoruz, gerisine karışmıyoruz)
        for (Tower tower : builtTowers) {
            if (tower != null) {
                tower.update(enemies); // İsmi updateAttack yerine update yaptık
            }
        }

        gameRender.setGameOver(isGameOver);
    }

    private void handleMouseInput() {
        boolean isMousePressed = ui.StdDraw.isMousePressed();

        if (isMousePressed && !wasMousePressed) {
            double mouseX = ui.StdDraw.mouseX();
            double mouseY = ui.StdDraw.mouseY();
            int currentGold = hudBar.getGold();

            for (int i = 0; i < towerSlots.size(); i++) {
                double[] slot = towerSlots.get(i);
                Tower currentTower = builtTowers.get(i);

                // Slot boşsa ve build butonuna tıklandıysa
                if (currentTower == null) {
                    if (buildButtons.get(i).isClicked(mouseX, mouseY) && currentGold >= 100) {
                        hudBar.setGold(currentGold - 100);
                        builtTowers.set(i, new LevelOneTower(slot[0], slot[1]));
                        upgradeButtons[i] = new UpgradeButton(slot[0], slot[1] - 0.06, 0.06, 0.03, 300);
                        break;
                    }
                }
                // Slot doluysa ve upgrade butonuna tıklandıysa
                // Slot doluysa ve upgrade butonuna tıklandıysa
                // Slot doluysa ve upgrade butonuna tıklandıysa
                else if (currentTower.getLevel() < 3 && upgradeButtons[i] != null) {
                    if (upgradeButtons[i].isClicked(mouseX, mouseY)) {

                        // 🌟 SİHİRLİ DOKUNUŞ: Java'ya soruyoruz:
                        // "Evet bu bir Tower ama, acaba arkada IUpgradable yeteneğine de sahip mi?"
                        if (currentTower instanceof Model.Base.IUpgradable) {

                            int cost = currentTower.getUpgradeCost();
                            if (currentGold >= cost) {
                                hudBar.setGold(currentGold - cost);

                                // 🌟 TİP DÖNÜŞÜMÜ (CASTING):
                                // Java'ya diyoruz ki: "Ben garanti ediyorum, bu kule upgrade edilebilir.
                                // Şimdi onu geçici olarak IUpgradable tipine dönüştür ve upgrade() metodunu çağır."
                                Tower upgradedTower = ((Model.Base.IUpgradable) currentTower).upgrade();
                                builtTowers.set(i, upgradedTower);

                                // KONTROLÜ YENİ KULEYE GÖRE YAPIYORUZ:
                                if (upgradedTower.getLevel() == 2) {
                                    upgradeButtons[i] = new UpgradeButton(slot[0], slot[1] - 0.06, 0.06, 0.03, 500);
                                }
                                else if (upgradedTower.getLevel() == 3) {
                                    upgradeButtons[i] = null;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        wasMousePressed = isMousePressed;
    }

    public void render() {
        gameRender.render();
    }

    public List<Tower> getBuiltTowers() { return builtTowers; }
    public List<Enemy> getEnemies() { return enemies; }
    public HudBar getHudBar() { return hudBar; }
}