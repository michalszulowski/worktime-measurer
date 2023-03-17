package app.backend.concurrency;

public abstract class AbstractControllableProcess implements ConcurrentProcess {
    private final Thread runThread;
    private final String name;
    private boolean running; //TODO maybe do volatile

    public AbstractControllableProcess(String name) {
        runThread = constructThread(name);
        this.name = name;
        running = false;
    }

    @Override
    public void start() {
        running = true;
        runThread.start();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    protected abstract void performMainLoopBody();

    private Thread constructThread(String name) {
        Thread thread = new Thread(this::startMainLoop);
        thread.setName(name);
        //thread.setUncaughtExceptionHandler(); TODO implement
        return thread;
    }

    private void startMainLoop() {
        while (running) {
            try {
                performMainLoopBody();
                checkForPendingControlActions();
            } catch (ProcessKilledException ex) {
                running = false;
            }
        }
    }
}
