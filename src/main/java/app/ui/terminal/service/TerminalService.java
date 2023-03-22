package app.ui.terminal.service;

import app.ui.terminal.output.OutStream;

public interface TerminalService {
    TerminalSettings getSettings();
    void clear();
    OutStream getOutStream();
}
