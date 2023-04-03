package app.ui.terminal.impl;

import app.backend.engine.ActivitiesEngine;
import app.backend.engine.impl.local.LocalFilesystemEngine;
import app.backend.service.ActivitiesEngineService;
import app.backend.service.ActivitiesEngineServiceImpl;
import app.backend.time.CurrentTimeSupplier;
import app.lang.DirectLangMap;
import app.lang.UiLangMap;
import app.ui.terminal.impl.context.WelcomeScreenContext;
import app.ui.terminal.service.*;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;
import app.ui.terminal.input.FromStreamBufferedReader;
import app.ui.terminal.input.InputReader;
import command.Command;

import java.nio.file.Paths;

public class OneFrameInterface extends SingleProcessTerminalInterface {
    private final InputReader inputReader;
    private UiLangMap langMap;

    public OneFrameInterface(TerminalService terminalService, ActivitiesEngineService appService, UiLangMap langMap) {
        super(terminalService, appService);
        this.langMap = langMap;
        inputReader = new FromStreamBufferedReader(System.in);
        switchContext(new WelcomeScreenContext(this));
    }

    public UiLangMap getLangMap() {
        return langMap;
    }

    public TerminalService getTerminalService() {
        return terminalService;
    }

    @Override
    protected void performMainLoopBody() {
        showFrame();
        waitForInputAndInvoke();
    }

    private void showFrame() {
        terminalService.clear();
        context.printFrame();
    }

    private void waitForInputAndInvoke() {
        String input = inputReader.waitForAndRead();
        Command command = context.getCommandFactory().getCommand(input);
        command.invoke();
    }

    public static void main(String[] args) {
        TerminalSettings settings = new FixedSettings(new FixedSize(30, 50));
        TerminalService terminalService = new FixedSettingsLinuxTerminalService(settings);
        ActivitiesEngine appEngine = new LocalFilesystemEngine(Paths.get("/home/michal/Documents/work-records/"));
        ActivitiesEngineService appService = new ActivitiesEngineServiceImpl(appEngine, new CurrentTimeSupplier());
        OneFrameInterface terminalInterface = new OneFrameInterface(terminalService, appService, new DirectLangMap());
        terminalInterface.show();
    }

}
