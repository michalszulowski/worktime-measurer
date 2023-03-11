package app.backend.concurrency;

import app.backend.concurrency.state.ProcessState;

public abstract class AbstractConcurrentProcess implements ConcurrentProcess {
    private boolean running;

    public void run() {
        running = true;
        mainLoop();
    }

    public void checkForPendingControlActions() {
        //fetch new state if available
        ProcessState newState = null;
        if (newState == null) {
            return;
        }
        newState.executeOnActivation();
    }

    public boolean isRunning() {
        return running;
    }

    //temp
    private void mainLoop() {
        while (running) {
            loop();
            checkForPendingControlActions();
        }
    }

    protected void pause() {

    }

    protected void unpause() {

    }

    protected void kill() {

    }
}
