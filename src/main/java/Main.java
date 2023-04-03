import app.MeasurerApp;
import app.loader.ConfigFileOneFrameTerminalAppLoader;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        final String configFilePath = "./app_config.txt";
        MeasurerApp app = new ConfigFileOneFrameTerminalAppLoader(Paths.get(configFilePath)).load();
        app.start();
    }
}
