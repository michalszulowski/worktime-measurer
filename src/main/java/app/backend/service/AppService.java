package app.backend.service;

import app.backend.engine.Engine;
import app.backend.time.TimeSupplier;

public interface AppService <T extends Engine<?, ?>> {
    T getEngine();
    TimeSupplier getTimeSupplier();
}
