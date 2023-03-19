package app.ui.terminal.impl;

import app.backend.engine.ActivitiesEngine;
import app.lang.UiLangMap;
import app.ui.terminal.TerminalSettings;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;

public class OneFrameInterface extends TerminalInterface {
    private ActivitiesEngine appEngine;
    private UiLangMap langMap;

    public OneFrameInterface(TerminalSettings terminalSettings) {
        super(terminalSettings);
        switchContext(new ShowingCurrentDayContext(this));
    }

    public ActivitiesEngine getAppEngine() {
        return appEngine;
    }

    public UiLangMap getLangMap() {
        return langMap;
    }

    @Override
    protected void performMainLoopBody() {
        showFrame();
        waitForInput();
    }

    private void showFrame() {

    }

    private void waitForInput() {

    }


}
