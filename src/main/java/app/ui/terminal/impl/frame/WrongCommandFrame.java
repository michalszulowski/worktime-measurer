package app.ui.terminal.impl.frame;

import app.backend.engine.ActivitiesEngine;
import app.backend.service.ActivitiesEngineService;
import app.lang.UiLangMap;
import app.ui.terminal.service.TerminalService;
import app.ui.terminal.output.frame.AppFrame;

public class WrongCommandFrame extends AppFrame {
    private final String enteredCommand;

    public WrongCommandFrame(TerminalService terminalService, ActivitiesEngineService appService, UiLangMap langMap, String enteredCommand) {
        super(terminalService, appService, langMap);
        this.enteredCommand = enteredCommand;
    }

    @Override
    protected void printContent() {
        terminalService.getOutStream().println(langMap.getText("Typed wrong command") + ": " + enteredCommand);
    }
}
