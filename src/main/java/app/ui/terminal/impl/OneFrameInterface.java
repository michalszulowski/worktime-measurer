package app.ui.terminal.impl;

import app.backend.engine.ActivitiesEngine;
import app.backend.service.ActivitiesEngineService;
import app.lang.UiLangMap;
import app.ui.terminal.FixedSettings;
import app.ui.terminal.FixedSize;
import app.ui.terminal.TerminalSettings;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;
import org.jline.terminal.TerminalBuilder;

public class OneFrameInterface extends TerminalInterface {
    private UiLangMap langMap;

    public OneFrameInterface(TerminalSettings terminalSettings, ActivitiesEngineService appService, UiLangMap langMap) {
        super(terminalSettings, appService);
        this.langMap = langMap;
        switchContext(new ShowingCurrentDayContext(this));
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
        context.printFrame();
    }

    private void waitForInput() {

    }

}
