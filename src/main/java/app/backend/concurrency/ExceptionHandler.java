package app.backend.concurrency;

public interface ExceptionHandler<T extends Exception> {
    void handle(T exception);
}
