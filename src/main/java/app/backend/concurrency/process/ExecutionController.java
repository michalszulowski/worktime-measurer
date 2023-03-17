package app.backend.concurrency.process;

/**
 * Gives synchronized methods for controlling thread.
 */
public interface ExecutionController {
    void run(); //TODO run will not work if process did not start
    void kill();
    void pause();
    void unpause();
}
