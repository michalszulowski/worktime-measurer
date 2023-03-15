package app.backend.concurrency.state;

import app.backend.concurrency.AbstractControllableProcess;
import app.backend.concurrency.ProcessKilledException;

public class KilledState extends StateWithOwner {
    public KilledState(AbstractControllableProcess owner) {
        super(owner, "KILLED");
    }

    @Override
    public void executeOnActivation() {
        throw new ProcessKilledException();
    }
}
