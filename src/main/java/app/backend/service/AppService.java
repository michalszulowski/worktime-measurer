package app.backend.service;

import app.backend.engine.Engine;
import app.backend.time.TimeSupplier;
import app.day.WorkDay;

import java.util.Optional;

public interface AppService <U extends WorkDay<?>, T extends Engine<?, U>> {
    T getEngine();
    TimeSupplier getTimeSupplier();
    Optional<U> getActiveDay();
}
