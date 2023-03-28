package app.ui.terminal.service;

public class FixedSettingsLinuxTerminalService extends LinuxTerminalService {
    private final TerminalSettings settings;

    public FixedSettingsLinuxTerminalService(TerminalSettings settings) {
        this.settings = settings;
    }

    @Override
    public TerminalSettings getSettings() {
        return settings;
    }
}
