package app.loader;

import app.backend.engine.ActivitiesEngine;
import app.backend.engine.impl.local.LocalFilesystemEngine;
import app.impl.OneFrameTerminalApp;
import app.ui.terminal.service.*;
import io.TextFileToDictionaryReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ConfigFileOneFrameTerminalAppLoader implements AppLoader {
    private final Path configFile;
    private final Map<String, String> configDictionary;

    public ConfigFileOneFrameTerminalAppLoader(Path configFile) {
        this.configFile = configFile;
        configDictionary = new TextFileToDictionaryReader().read(configFile);
    }

    @Override
    public OneFrameTerminalApp load() {
        ActivitiesEngine appEngine = createAppEngine();
        TerminalService terminalService = loadTerminalService();
        return new OneFrameTerminalApp(appEngine, terminalService);
    }

    private ActivitiesEngine createAppEngine() {
        String filesystemEngineRoot = configDictionary.get("filesystem_engine_root");
        return new LocalFilesystemEngine(Paths.get(filesystemEngineRoot));
    }

    private TerminalService loadTerminalService() {
        int terminalWidth = Integer.parseInt(configDictionary.get("terminal.width"));
        int terminalHeight = Integer.parseInt(configDictionary.get("terminal.height"));
        TerminalSettings settings = new FixedSettings(new FixedSize(terminalWidth, terminalHeight));
        return new FixedSettingsLinuxTerminalService(settings);
    }
}
