package core;

import ui.StdDraw;

/**
 * Manages the main game loop, handling simulation updates and rendering.
 */
public class GameLoop {
    private SimulationManager simulationManager;
    private boolean isRunning = true;
    private static final int FPS = 30;
    private static final int FRAME_TIME = 1000 / FPS;
    private long lastSecondTime = System.currentTimeMillis();

    /**
     * Initializes the game loop and starts the main game loop.
     */
    public GameLoop() {
        this.simulationManager = new SimulationManager();

        while (isRunning) {
            simulationManager.update();

            simulationManager.render();

            StdDraw.pause(FRAME_TIME);
        }
    }
}