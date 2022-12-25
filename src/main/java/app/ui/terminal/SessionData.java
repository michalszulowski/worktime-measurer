package app.ui.terminal;

import app.record.Activity;
import app.ui.terminal.output.OutStream;
import app.ui.terminal.output.TerminalFrameElement;

public class SessionData implements TerminalFrameElement {
    private OutStream outStream;

    @Override
    public void print() {

    }

    private void printActivity(Activity activity) {
        outStream.println(activity.getDescription());
    }
}
