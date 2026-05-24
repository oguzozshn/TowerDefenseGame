package Model.Base;

public abstract class Button {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    /**
     * Constructor for Button class.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Button(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if the button is clicked based on mouse coordinates.
     * @param mouseX The x-coordinate of the mouse.
     * @param mouseY The y-coordinate of the mouse.
     * @return True if the button is clicked, false otherwise.
     */
    public boolean isClicked(double mouseX, double mouseY) {
        return mouseX >= x - width &&
                mouseX <= x + width &&
                mouseY >= y - height &&
                mouseY <= y + height;
    }

    /**
     * Draws the button on the screen.
     */
    public abstract void draw();
}