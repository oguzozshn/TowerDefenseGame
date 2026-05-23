package core;

import ui.StdDraw;

public class GameLoop {
    private SimulationManager simulationManager;
    private boolean isRunning = true;

    private static final int FPS        = 30;
    private static final int FRAME_TIME = 1000 / FPS;

    private long lastSecondTime = System.currentTimeMillis();

    public GameLoop() {
        // SimulationManager'ı oluştur
        this.simulationManager = new SimulationManager();

        while (isRunning) {
            // Oyun mantığını güncelle
            simulationManager.update();
            
            // Renderla
            simulationManager.render();

            // Her geçen gerçek saniyede tick()
            long now = System.currentTimeMillis();
            if (now - lastSecondTime >= 1000) {
                simulationManager.getHudBar().tick();
                lastSecondTime = now;
            }

            StdDraw.pause(FRAME_TIME);
        }
    }
}