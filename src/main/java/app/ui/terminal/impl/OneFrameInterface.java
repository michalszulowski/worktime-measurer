package app.ui.terminal.impl;

import app.backend.service.ActivitiesEngineService;
import app.lang.UiLangMap;
import app.ui.terminal.TerminalSettings;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;
import app.ui.terminal.input.FromStreamBufferedReader;
import app.ui.terminal.input.InputReader;
import command.Command;

public class OneFrameInterface extends TerminalInterface {
    private final InputReader inputReader;
    private UiLangMap langMap;

    public OneFrameInterface(TerminalSettings terminalSettings, ActivitiesEngineService appService, UiLangMap langMap) {
        super(terminalSettings, appService);
        this.langMap = langMap;
        inputReader = new FromStreamBufferedReader(System.in);
        switchContext(new ShowingCurrentDayContext(this));
    }

    public UiLangMap getLangMap() {
        return langMap;
    }

    @Override
    protected void performMainLoopBody() {
        showFrame();
        waitForInputAndInvoke();
    }

    private void showFrame() {
        context.printFrame();
    }

    private void waitForInputAndInvoke() {
        String input = inputReader.waitForAndRead();
        Command command = context.getCommandFactory().getCommand(input);
        command.invoke();
    }

}
