package app.ui.terminal.impl.context;

import app.ui.terminal.impl.TerminalInterface;
import command.factory.CommandFactory;

public interface TerminalContext <T extends TerminalInterface> {
    CommandFactory getCommandFactory();
    void printFrame();
    T getOwner();
}
