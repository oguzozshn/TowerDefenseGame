package ui;

import java.awt.Font;

/**
 * Represents the Heads-Up Display (HUD) bar for displaying game information.
 */
public class HudBar {
    private int elapsedSeconds = 0;
    private int gold;
    private int baseHealth;
    private int score;

    private static final double BAR_Y      = 0.0375;
    private static final double BAR_HEIGHT = 0.075;

    public HudBar(int startGold, int baseHealth, int score) {
        this.gold       = startGold;
        this.baseHealth = baseHealth;
        this.score = score;
    }

    /**
     * Sets the score of the player.
     * @param score
     */
    public void setScore(int score) { this.score = score; }

    /**
     * Increments the elapsed time by one second.
     */
    public void tick() {
        elapsedSeconds++;
    }

    /**
     * @return The current gold amount.
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Sets the gold amount.
     * @param gold
     */
    public void setGold(int gold) { this.gold = gold; }

    /**
     * Sets the base health of the player.
     * @param health
     */
    public void setBaseHealth(int health) { this.baseHealth = health; }

    /**
     * Retrieves the current score of the player.
     * @return The current score.
     */
    public int getScore() { return score; }

    /**
     * Draws the HUD bar on the screen.
     */
    public void draw() {
        StdDraw.setPenColor(new java.awt.Color(17, 24, 39));
        StdDraw.filledRectangle(0.5, BAR_Y, 0.5, BAR_HEIGHT);

        StdDraw.setPenColor(new java.awt.Color(55, 65, 81));
        StdDraw.setPenRadius(0.003);
        StdDraw.line(0.0, BAR_Y + BAR_HEIGHT, 1.0, BAR_Y + BAR_HEIGHT);
        StdDraw.setPenRadius();

        int minutes = elapsedSeconds / 60;
        int seconds = elapsedSeconds % 60;
        String timeStr = String.format("%02d:%02d", minutes, seconds);

        StdDraw.setPenColor(new java.awt.Color(156, 163, 175));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 11));
        StdDraw.text(0.12, BAR_Y + 0.018, "TIME");

        StdDraw.setPenColor(new java.awt.Color(249, 250, 251));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
        StdDraw.text(0.12, BAR_Y - 0.010, timeStr);

        StdDraw.setPenColor(new java.awt.Color(156, 163, 175));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 11));
        StdDraw.text(0.31, BAR_Y + 0.018, "BASE HP");

        String baseHealthStr = String.format("%02d", baseHealth);
        StdDraw.setPenColor(new java.awt.Color(239, 68, 68));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
        StdDraw.text(0.31, BAR_Y - 0.010, baseHealthStr);

        String score = String.format("%02d", getScore());
        StdDraw.setPenColor(new java.awt.Color(239, 68, 68));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
        StdDraw.text(0.55, BAR_Y - 0.010, score);

        StdDraw.setPenColor(new java.awt.Color(156, 163, 175));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 11));
        StdDraw.text(0.88, BAR_Y + 0.018, "GOLD");

        StdDraw.setPenColor(new java.awt.Color(251, 191, 36));
        StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
        StdDraw.text(0.88, BAR_Y - 0.010, gold + " \u2726");
    }
}