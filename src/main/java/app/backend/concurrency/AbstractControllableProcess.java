package app.backend.concurrency;

import app.backend.concurrency.state.ProcessState;

//TODO maybe break into two classes, one with only synchronized functionalities
public abstract class AbstractControllableProcess implements ControllableProcess {
    private final Thread runThread;
    private boolean running;
    private volatile ProcessState currentState;
    private final SynchronizedValueKeeper<ProcessState> setStateKeeper;
    private final ExecutionController executionController;

    public AbstractControllableProcess() {
        runThread = new Thread(this::startMainLoop);
        //TODO set name and exception handler
        running = false;
        setStateKeeper = new SynchronizedValueKeeper<>();
        executionController = null; //TODO implement
    }

    @Override
    public void start() {
        running = true;
        runThread.start();
    }

    //TODO think about synchronizing
    @Override
    public void checkForPendingControlActions() {
        setStateKeeper.pull()
                .ifPresent(this::activateNewState);
    }

    @Override
    public ProcessState getState() {
        return currentState;
    }

    @Override
    public void setNewState(ProcessState state) {
        setStateKeeper.set(state);
    }

    @Override
    public ExecutionController getExecutionController() {
        return executionController;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    protected abstract void performMainLoopBody();

    //TODO think about synchronizing this
    private void activateNewState(ProcessState state) {
        currentState = state;
        state.executeOnActivation();
    }

    private void startMainLoop() {
        while (running) {
            performMainLoopBody();
            checkForPendingControlActions();
        }
    }
}
