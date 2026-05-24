package ui;

/**
 * Represents the game over a screen that is displayed when the player loses.
 */
public class GameOverScreen {
    private HudBar hudBar;

    /**
     * Constructor for GameOverScreen.
     * @param hudBar
     */
    public GameOverScreen(HudBar hudBar) {
        this.hudBar = hudBar;
    }

    /**
     * Draws the game over screen with relevant information.
     */
    public void Draw() {
        StdDraw.setPenColor(new java.awt.Color(0, 0, 0, 180));
        StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.5);

        StdDraw.setPenColor(new java.awt.Color(239, 68, 68));
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 54));
        StdDraw.text(0.5, 0.55, "GAME OVER");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        StdDraw.text(0.5, 0.45, "Your castle is destoyed. Defence failed!");

        StdDraw.text(0.5, 0.38, "Your Score");
        String score = String.valueOf(hudBar.getScore());
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        StdDraw.text(0.5, 0.32, score);

        StdDraw.show();
    }
}