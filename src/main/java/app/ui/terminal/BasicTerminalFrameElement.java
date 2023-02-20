package app.ui.terminal;

import app.lang.UiLangMap;
import app.ui.terminal.output.OutStream;
import app.ui.terminal.output.TerminalFrameElement;

public abstract class BasicTerminalFrameElement implements TerminalFrameElement {
    protected OutStream outStream;
    protected UiLangMap langMap;

    public BasicTerminalFrameElement(OutStream outStream, UiLangMap langMap) {
        this.outStream = outStream;
        this.langMap = langMap;
    }
}
