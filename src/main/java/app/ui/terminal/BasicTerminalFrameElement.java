package app.ui.terminal;

import app.lang.UiLangMap;
import app.ui.terminal.output.AppFrame;
import app.ui.terminal.output.OutStream;
import app.ui.terminal.output.TerminalFrameElement;

public abstract class BasicTerminalFrameElement implements TerminalFrameElement {
    protected AppFrame parent;
    protected OutStream outStream;
    protected UiLangMap langMap;

    public BasicTerminalFrameElement(AppFrame parent) {
        this.parent = parent;
        this.outStream = parent.getOutStream();
        this.langMap = parent.getLangMap();
    }
}
