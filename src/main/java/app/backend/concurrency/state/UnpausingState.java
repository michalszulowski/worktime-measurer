package app.backend.concurrency.state;

import app.backend.concurrency.AbstractControllableProcess;

public class UnpausingState extends StateWithOwner {

    public UnpausingState(AbstractControllableProcess owner) {
        super(owner, "UNPAUSING");
    }

    @Override
    public void executeOnActivation() {
        owner.getExecutionController().run();
    }
}
