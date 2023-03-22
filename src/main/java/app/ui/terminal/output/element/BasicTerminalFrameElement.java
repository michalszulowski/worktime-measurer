package app.ui.terminal.output.element;

import app.lang.UiLangMap;
import app.ui.terminal.output.frame.AppFrame;
import app.ui.terminal.output.OutStream;

public abstract class BasicTerminalFrameElement implements TerminalFrameElement {
    protected AppFrame parent;
    protected OutStream outStream;
    protected UiLangMap langMap;

    public BasicTerminalFrameElement(AppFrame parent) {
        this.parent = parent;
        this.outStream = parent.getTerminalService().getOutStream();
        this.langMap = parent.getLangMap();
    }
}
