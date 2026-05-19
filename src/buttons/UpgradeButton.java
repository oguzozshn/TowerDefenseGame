package buttons;

import ui.StdDraw;

public class UpgradeButton {
    double buttonX;
    double buttonY;
    double buttonWidth;
    double buttonHeight;
    private int upgradeCost;

    public UpgradeButton(double x, double y, double width, double height, int cost) {
        this.buttonX = x;
        this.buttonY = y;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.upgradeCost = cost;
    }

    public void draw() {
        // Buton arka planı
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(buttonX, buttonY, buttonWidth, buttonHeight);

        // Buton kenarı
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.002);
        StdDraw.rectangle(buttonX, buttonY, buttonWidth, buttonHeight);

        // "UPGRADE" yazısı
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        StdDraw.text(buttonX, buttonY + 0.01, "UPGRADE");
        StdDraw.text(buttonX, buttonY - 0.01, upgradeCost + "G");
    }

    public boolean isClicked(double mouseX, double mouseY) {
        return mouseX >= buttonX - buttonWidth &&
                mouseX <= buttonX + buttonWidth &&
                mouseY >= buttonY - buttonHeight &&
                mouseY <= buttonY + buttonHeight;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }
}
