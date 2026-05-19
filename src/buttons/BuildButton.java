package buttons;
import ui.StdDraw;

public class BuildButton {
    double buildButtonX;
    double buildButtonY;
    double buildButtonWidth;
    double buildButtonHeight;

    public BuildButton(double buildButtonX, double buildButtonY, double buildButtonWidth, double buildButtonHeight){
        this.buildButtonX = buildButtonX;
        this.buildButtonY = buildButtonY;
        this.buildButtonWidth = buildButtonWidth;
        this.buildButtonHeight = buildButtonHeight;
    };

    public void draw(){
        // Buton arka planı
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(buildButtonX, buildButtonY, buildButtonWidth, buildButtonHeight);

        // Buton kenarı
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(0.002);
        StdDraw.rectangle(buildButtonX, buildButtonY, buildButtonWidth, buildButtonHeight);

        // "BUILD" yazısı
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        StdDraw.text(buildButtonX, buildButtonY, "BUILD");
    }

    public boolean isClicked(double mouseX, double mouseY) {
        return mouseX >= buildButtonX - buildButtonWidth &&
                mouseX <= buildButtonX + buildButtonWidth &&
                mouseY >= buildButtonY - buildButtonHeight &&
                mouseY <= buildButtonY + buildButtonHeight;
    }
}
