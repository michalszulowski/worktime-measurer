package app.ui.terminal.impl.context;

import command.factory.CommandFactory;

public interface TerminalContext {
    CommandFactory getCommandFactory();
    void printFrame();
}
