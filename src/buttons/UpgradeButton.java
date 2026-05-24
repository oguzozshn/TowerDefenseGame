package buttons;

import ui.StdDraw;
import Model.Base.Button;

/**
 * Represents an upgrade button in the game.
 */
public class UpgradeButton extends Button {
    private int upgradeCost;

    /**
     * Constructs an upgrade button with a specified position, size, and upgrade cost.
     *
     * @param x      The x-coordinate of the button's center.
     * @param y      The y-coordinate of the button's center.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param cost   The cost required to upgrade.
     */
    public UpgradeButton(double x, double y, double width, double height, int cost) {
        // 🌟 KRİTİK ADIM: Değerleri doğrudan üst sınıfın (Button) constructor'ına gönderiyoruz
        super(x, y, width, height);
        this.upgradeCost = cost;
    }

    /**
     * Draws the upgrade button on the screen.
     */
    @Override
    public void draw() {
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(x, y, width, height);

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.002);
        StdDraw.rectangle(x, y, width, height);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        StdDraw.text(x, y + 0.01, "UPGRADE");
        StdDraw.text(x, y - 0.01, upgradeCost + "G");
    }
}