package buttons;

import ui.StdDraw;
import Model.Base.Button;

/**
 * Class representing a build button for tower placement.
 */
public class BuildButton extends Button {
    private final int buildCost;

    /**
     * Constructor for BuildButton.
     */
    public BuildButton(double buildButtonX, double buildButtonY, double buildButtonWidth, double buildButtonHeight, int upgradeCost){
        super(buildButtonX, buildButtonY, buildButtonWidth, buildButtonHeight);

        this.buildCost = 100;
    }

    /**
     * Draws the build button on the screen.
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
        StdDraw.text(x, y + 0.01, "BUILD");
        StdDraw.text(x, y - 0.01, buildCost + "G");
    }
}