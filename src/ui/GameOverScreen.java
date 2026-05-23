package ui;

public class GameOverScreen {
    public GameOverScreen() {};
    public void Draw() {
        StdDraw.setPenColor(new java.awt.Color(0, 0, 0, 180));
        StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.5);

        StdDraw.setPenColor(new java.awt.Color(239, 68, 68));
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 54));
        StdDraw.text(0.5, 0.55, "GAME OVER");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        StdDraw.text(0.5, 0.45, "Kalenizin canı tükendi. Savunma başarısız oldu!");

        StdDraw.show();
    };
}
