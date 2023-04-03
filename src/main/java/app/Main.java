package app;

import app.MeasurerApp;
import app.loader.AppLoader;
import app.loader.ConfigFileOneFrameTerminalAppLoader;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        final String configFilePath = "./app_config.txt";
        AppLoader appLoader = new ConfigFileOneFrameTerminalAppLoader(Paths.get(configFilePath));
        MeasurerApp app = appLoader.load();
        app.start();
    }
}
