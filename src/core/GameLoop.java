package core;

import ui.GameRender;
import ui.StdDraw;

public class GameLoop {
    private GameRender gameRender;
    private boolean isRunning = true;

    private static final int FPS        = 30;
    private static final int FRAME_TIME = 1000 / FPS;

    private long lastSecondTime = System.currentTimeMillis();  // ← sistem saati

    public GameLoop() {
        this.gameRender = new GameRender();

        while (isRunning) {
            gameRender.update();

            // Her geçen gerçek saniyede tick()
            long now = System.currentTimeMillis();
            if (now - lastSecondTime >= 1000) {
                gameRender.getHudBar().tick();
                lastSecondTime = now;
            }

            StdDraw.pause(FRAME_TIME);
        }
    }
}