package app.ui.terminal.impl.frame;

import app.backend.service.ActivitiesEngineService;
import app.lang.UiLangMap;
import app.ui.terminal.output.frame.AppFrame;
import app.ui.terminal.service.TerminalService;

public class WelcomeFrame extends AppFrame {
    public WelcomeFrame(TerminalService terminalService, ActivitiesEngineService appService, UiLangMap langMap) {
        super(terminalService, appService, langMap);
    }

    @Override
    protected void printContent() {
        outStream.println(langMap.getText("Welcome") + "!");
        outStream.println(langMap.getText("Enter a command"));
    }
}
