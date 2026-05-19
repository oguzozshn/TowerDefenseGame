package ui;

import java.awt.Font;

public class HudBar {
    private int elapsedSeconds = 0;
    private int gold;
    private int currentWave;
    private int totalWaves;

    // StdDraw koordinatları: bar ekranın en altında (y=0 ile y=0.075 arası)
    private static final double BAR_Y      = 0.0375;  // merkez
    private static final double BAR_HEIGHT = 0.075;

    public HudBar(int startGold, int totalWaves) {
        this.gold       = startGold;
        this.totalWaves = totalWaves;
        this.currentWave = 1;
    }

    // Her saniyede bir GameLoop'tan çağrılır
    public void tick() {
        elapsedSeconds++;
    }

    // HudBar.java içine eklenecek metod:
    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold)           { this.gold = gold; }
    public void setCurrentWave(int wave)    { this.currentWave = wave; }

    public void draw() {
        // Arka plan
        StdDraw.setPenColor(new java.awt.Color(17, 24, 39));        // #111827
        StdDraw.filledRectangle(0.5, BAR_Y, 0.5, BAR_HEIGHT);

        // Üst ayırıcı çizgi
        StdDraw.setPenColor(new java.awt.Color(55, 65, 81));        // #374151
        StdDraw.setPenRadius(0.003);
        StdDraw.line(0.0, BAR_Y + BAR_HEIGHT, 1.0, BAR_Y + BAR_HEIGHT);
        StdDraw.setPenRadius();

        // ── TIME (sol) ──────────────────────────────────────
        int minutes = elapsedSeconds / 60;
        int seconds = elapsedSeconds % 60;
        String timeStr = String.format("%02d:%02d", minutes, seconds);

        StdDraw.setPenColor(new java.awt.Color(156, 163, 175));     // label rengi
        StdDraw.setFont(new Font("Arial", Font.BOLD, 11));
        StdDraw.text(0.12, BAR_Y + 0.018, "TIME");

        StdDraw.setPenColor(new java.awt.Color(249, 250, 251));     // değer rengi
        StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
        StdDraw.text(0.12, BAR_Y - 0.010, timeStr);

        // ── WAVE (orta) ─────────────────────────────────────
        StdDraw.setPenColor(new java.awt.Color(156, 163, 175));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 11));
        StdDraw.text(0.5, BAR_Y + 0.018, "WAVE");

        StdDraw.setPenColor(new java.awt.Color(249, 250, 251));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
        StdDraw.text(0.5, BAR_Y - 0.010, currentWave + " / " + totalWaves);

        // ── GOLD (sağ) ──────────────────────────────────────
        StdDraw.setPenColor(new java.awt.Color(156, 163, 175));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 11));
        StdDraw.text(0.88, BAR_Y + 0.018, "GOLD");

        StdDraw.setPenColor(new java.awt.Color(251, 191, 36));      // altın sarısı
        StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
        StdDraw.text(0.88, BAR_Y - 0.010, gold + " \u2726");
    }
}