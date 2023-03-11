package app.backend.concurrency;

/**
 * Gives synchronized methods for controlling thread.
 */
public interface ExecutionController {
    void run();
    void kill();
    void pause();
    void unpause();
}
