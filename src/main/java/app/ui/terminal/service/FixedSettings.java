package app.ui.terminal.service;

public class FixedSettings implements TerminalSettings {
    private final TerminalSize size;

    public FixedSettings(TerminalSize size) {
        this.size = size;
    }

    @Override
    public TerminalSize getSize() {
        return size;
    }
}
