package app.backend.concurrency;

import java.util.Optional;

//TODO write tests
public class SynchronizedValueKeeper<T> implements ValueKeeper<T> {
    private T value;

    @Override
    public synchronized Optional<T> pull() {
        T oldValue = value;
        value = null;
        return Optional.ofNullable(oldValue);
    }

    @Override
    public synchronized void set(T newVal) {
        value = newVal;
    }
}
