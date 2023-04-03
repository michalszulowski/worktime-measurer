package app.loader;

import app.backend.engine.ActivitiesEngine;
import app.impl.OneFrameTerminalApp;
import app.ui.terminal.service.TerminalService;
import io.TextFileToDictionaryReader;

import java.nio.file.Path;
import java.util.Map;

public class ConfigFileOneFrameTerminalAppLoader implements AppLoader {
    private final Path configFile;
    private final Map<String, String> configurationDictionary;

    public ConfigFileOneFrameTerminalAppLoader(Path configFile) {
        this.configFile = configFile;
        configurationDictionary = new TextFileToDictionaryReader().read(configFile);
    }

    @Override
    public OneFrameTerminalApp load() {
        ActivitiesEngine appEngine = createAppEngine();
        TerminalService terminalService = loadTerminalService();
        return new OneFrameTerminalApp(appEngine, terminalService);
    }

    private ActivitiesEngine createAppEngine() {

    }

    private TerminalService loadTerminalService() {

    }
}
