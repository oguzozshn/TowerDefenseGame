package buttons;
import ui.StdDraw;

/**
 * Class representing a build button for tower placement.
 */
public class BuildButton {
    double buildButtonX;
    double buildButtonY;
    double buildButtonWidth;
    double buildButtonHeight;
    private final int buildCost;

    /**
     * Constructor for BuildButton.
     * @param buildButtonX X-coordinate of the button position.
     * @param buildButtonY Y-coordinate of the button position.
     * @param buildButtonWidth Width of the button.
     * @param buildButtonHeight Height of the button.
     * @param upgradeCost Cost to build the tower.
     */
    public BuildButton(double buildButtonX, double buildButtonY, double buildButtonWidth, double buildButtonHeight, int upgradeCost){
        this.buildButtonX = buildButtonX;
        this.buildButtonY = buildButtonY;
        this.buildButtonWidth = buildButtonWidth;
        this.buildButtonHeight = buildButtonHeight;
        this.buildCost = 100;
    }

    /**
     * Draws the build button on the screen.
     */
    public void draw(){
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(buildButtonX, buildButtonY, buildButtonWidth, buildButtonHeight);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(0.002);
        StdDraw.rectangle(buildButtonX, buildButtonY, buildButtonWidth, buildButtonHeight);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        StdDraw.text(buildButtonX, buildButtonY + 0.01, "BUILD");
        StdDraw.text(buildButtonX, buildButtonY - 0.01, buildCost + "G");
    }

    /**
     * Checks if the mouse click is within the build button's bounds.
     * @param mouseX X-coordinate of the mouse click.
     * @param mouseY Y-coordinate of the mouse click.
     * @return True if the click is within the button bounds, false otherwise.
     */
    public boolean isClicked(double mouseX, double mouseY) {
        return mouseX >= buildButtonX - buildButtonWidth &&
                mouseX <= buildButtonX + buildButtonWidth &&
                mouseY >= buildButtonY - buildButtonHeight &&
                mouseY <= buildButtonY + buildButtonHeight;
    }
}
