package app.backend.concurrency.state;

public interface ProcessState {
    void executeOnActivation();
    String getName();
}
