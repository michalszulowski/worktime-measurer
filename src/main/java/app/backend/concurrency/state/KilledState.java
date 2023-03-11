package app.backend.concurrency.state;

import app.backend.concurrency.AbstractConcurrentProcess;
import app.backend.concurrency.ProcessKilledException;

public class KilledState extends StateWithOwner {
    public KilledState(AbstractConcurrentProcess owner) {
        super(owner, "KILLED");
    }

    @Override
    public void executeOnActivation() {
        throw new ProcessKilledException();
    }
}
