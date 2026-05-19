package ui;

import Model.Base.Tower;
import tower.*;
import buttons.*;
import java.util.ArrayList;
import java.util.List;
import enemy.*;

public class GameRender {
    int canvas_width = 800;
    int canvas_height = 600;
    private HudBar hudBar;

    private boolean wasMousePressed = false;

    private List<BuildButton> buildButtons = new ArrayList<>();
    private List<Tower> builtTowers = new ArrayList<>();
    private UpgradeButton[] upgradeButtons; // Her slot için upgrade butonu takibi

    private List<double[]> towerSlots = List.of(
            new double[]{0.3216, 0.825},
            new double[]{0.6982, 0.800},
            new double[]{0.5091, 0.58},
            new double[]{0.2763, 0.42},
            new double[]{0.6429, 0.39}
    );
    private boolean[] slotBuilt;

    private List<double[]> pathWaypoints = List.of(
            new double[]{0.0, 0.48},   // Sol giriş
            new double[]{0.35, 0.48},  // Yukarı dönüş
            new double[]{0.50, 0.73},  // Tepe noktası
            new double[]{0.65, 0.48},  // Aşağı dönüş bükümü
            new double[]{0.86, 0.48}   // Kale önü
    );

    private List<Model.Base.Enemy> enemies = new ArrayList<>();
    private int spawnCooldown = 0;

    public GameRender() {
        StdDraw.setCanvasSize(canvas_width, canvas_height);
        StdDraw.picture(0.5, 0.5, "Assets/BackPlan.png", 1.0, 1.0);

        this.hudBar = new HudBar(1000, 5); // 500 başlangıç altını

        slotBuilt = new boolean[towerSlots.size()];
        upgradeButtons = new UpgradeButton[towerSlots.size()]; // Dizi boyutunu belirle

        for (double[] slot : towerSlots) {
            buildButtons.add(new BuildButton(slot[0], slot[1] - 0.06, 0.06, 0.04));
            builtTowers.add(null); // Başlangıçta kule yok, boş slot
        }
    }

    public void update() {
        // Arkaplan çizimi
        StdDraw.picture(0.5, 0.5, "Assets/BackPlan.png", 1.0, 1.0);

        // Kuleleri ve Butonları Çizdirme
        for (int i = 0; i < towerSlots.size(); i++) {
            if (slotBuilt[i] && builtTowers.get(i) != null) {
                builtTowers.get(i).Draw(); // Kuleyi çiz

                // Eğer kule son seviye (Level 3) değilse upgrade butonunu altında çizdir
                if (builtTowers.get(i).getLevel() < 3 && upgradeButtons[i] != null) {
                    upgradeButtons[i].draw();
                }
            } else {
                buildButtons.get(i).draw(); // İnşa edilmemişse BUILD butonunu çiz
            }
        }

        spawnCooldown++;
        if (spawnCooldown >= 45) { // Yaklaşık 1.5 saniyede bir
            double startX = pathWaypoints.get(0)[0];
            double startY = pathWaypoints.get(0)[1];

            // %70 ihtimalle Goblin, %30 ihtimalle Golem doğsun
            if (Math.random() < 0.7) {
                enemies.add(new enemy.Goblin(startX, startY));
            } else {
                enemies.add(new enemy.Golem(startX, startY));
            }
            spawnCooldown = 0;
        }

        // DÜŞMANLARI GÜNCELLE VE ÇİZ
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Model.Base.Enemy e = enemies.get(i);

            // Waypoint listesini parametre olarak gönderiyoruz
            e.update(pathWaypoints);
            e.Draw();

            if (e.isReachedCastle()) {
                enemies.remove(i);
                System.out.println("Bir düşman kaleyi kuşattı!");
            }
        }

        // Mouse tıklamalarını kontrol et
        boolean isMousePressed = StdDraw.isMousePressed();
        if (isMousePressed && !wasMousePressed) {
            double mouseX = StdDraw.mouseX();
            double mouseY = StdDraw.mouseY();
            int currentGold = hudBar.getGold();

            for (int i = 0; i < towerSlots.size(); i++) {
                double[] slot = towerSlots.get(i);

                // 1. DURUM: Slot boşsa ve BUILD butonuna tıklandıysa
                if (!slotBuilt[i]) {
                    if (buildButtons.get(i).isClicked(mouseX, mouseY)) {
                        if (currentGold >= 100) { // Level 1 Maliyeti: 100
                            hudBar.setGold(currentGold - 100);
                            slotBuilt[i] = true;
                            builtTowers.set(i, new LevelOneTower(slot[0], slot[1]));

                            // Kule altına Lvl 2'ye geçiş için 300 Gold isteyen Upgrade butonu ekle
                            upgradeButtons[i] = new UpgradeButton(slot[0], slot[1] - 0.06, 0.06, 0.03, 300);
                            System.out.println("Kule inşa edildi! Kalan Altın: " + hudBar.getGold());
                        } else {
                            System.out.println("Yetersiz altın! Kule inşası için 100 Gold gerekiyor.");
                        }
                        break;
                    }
                }
                // 2. DURUM: Slot doluysa ve UPGRADE butonuna tıklandıysa
                else {
                    Tower currentTower = builtTowers.get(i);
                    if (currentTower != null && currentTower.getLevel() < 3 && upgradeButtons[i] != null) {
                        if (upgradeButtons[i].isClicked(mouseX, mouseY)) {
                            int cost = currentTower.getUpgradeCost();

                            if (currentGold >= cost) {
                                hudBar.setGold(currentGold - cost);

                                if (currentTower.getLevel() == 1) {
                                    // Level 1 -> Level 2 yükseltmesi
                                    builtTowers.set(i, new LevelTwoTower(slot[0], slot[1]));
                                    // Yeni upgrade maliyeti Level 3 için 500 Gold oluyor
                                    upgradeButtons[i] = new UpgradeButton(slot[0], slot[1] - 0.06, 0.06, 0.03, 500);
                                    System.out.println("Kule Seviye 2'ye yükseltildi! Kalan Altın: " + hudBar.getGold());
                                } else if (currentTower.getLevel() == 2) {
                                    // Level 2 -> Level 3 yükseltmesi (Son Seviye)
                                    builtTowers.set(i, new LevelThreeTower(slot[0], slot[1]));
                                    upgradeButtons[i] = null; // Max seviyeye ulaştığı için butonu kaldırıyoruz
                                    System.out.println("Kule Son Seviye 3'e yükseltildi! Kalan Altın: " + hudBar.getGold());
                                }
                            } else {
                                System.out.println("Yetersiz altın! Geliştirme için " + cost + " Gold gerekiyor.");
                            }
                            break;
                        }
                    }
                }
            }
        }
        wasMousePressed = isMousePressed;

        StdDraw.enableDoubleBuffering();
        hudBar.draw();
        StdDraw.show();
    }

    public HudBar getHudBar() { return hudBar; }
}