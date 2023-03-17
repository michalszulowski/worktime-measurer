package app.backend.concurrency.state;

import app.backend.concurrency.process.ConcurrentProcess;
import app.backend.concurrency.process.ProcessKilledException;

public class KilledState extends StateWithOwner {
    public KilledState(ConcurrentProcess owner) {
        super(owner, "KILLED");
    }

    @Override
    public void executeOnActivation() {
        throw new ProcessKilledException();
    }
}
