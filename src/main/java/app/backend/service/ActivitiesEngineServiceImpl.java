package app.backend.service;

import app.backend.engine.ActivitiesEngine;
import app.backend.time.TimeSupplier;

public class ActivitiesEngineServiceImpl implements ActivitiesEngineService {
    private final ActivitiesEngine engine;
    private final TimeSupplier timeSupplier;

    public ActivitiesEngineServiceImpl(ActivitiesEngine engine, TimeSupplier timeSupplier) {
        this.engine = engine;
        this.timeSupplier = timeSupplier;
    }

    @Override
    public ActivitiesEngine getEngine() {
        return engine;
    }

    @Override
    public TimeSupplier getTimeSupplier() {
        return timeSupplier;
    }
}
