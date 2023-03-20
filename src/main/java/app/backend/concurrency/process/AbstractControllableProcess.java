package app.backend.concurrency.process;

public abstract class AbstractControllableProcess implements ConcurrentProcess {
    private final Thread runThread;
    private final String name;
    private boolean running;

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

    @Override
    public String getName() {
        return name;
    }

    protected abstract void performMainLoopBody();

    private Thread constructThread(String name) {
        Thread thread = new Thread(this::startMainLoop);
        thread.setName(name);
        //thread.setUncaughtExceptionHandler();
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
