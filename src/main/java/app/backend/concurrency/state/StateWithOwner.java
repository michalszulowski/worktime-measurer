package app.backend.concurrency.state;

import app.backend.concurrency.AbstractConcurrentProcess;

public abstract class StateWithOwner implements ProcessState {
    protected final AbstractConcurrentProcess owner;
    private final String name;

    public StateWithOwner(AbstractConcurrentProcess owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
