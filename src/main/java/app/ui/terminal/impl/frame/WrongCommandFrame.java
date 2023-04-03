package app.ui.terminal.impl.frame;

import app.backend.service.ActivitiesEngineService;
import app.lang.UiLangMap;
import app.ui.terminal.service.TerminalService;
import app.ui.terminal.output.frame.AppFrame;

public class WrongCommandFrame extends AppFrame {
    private final String message;

    public WrongCommandFrame(TerminalService terminalService, ActivitiesEngineService appService, UiLangMap langMap, String message) {
        super(terminalService, appService, langMap);
        this.message = message;
    }

    @Override
    protected void printContent() {
        outStream.println(langMap.getText("Typed wrong command") + ": ");
        outStream.println(message);
    }
}
