package app.backend.concurrency.state;

import app.backend.concurrency.ControllableProcess;

public abstract class StateWithOwner implements ProcessState {
    protected final ControllableProcess owner;
    private final String name;

    public StateWithOwner(ControllableProcess owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
