package app.ui.terminal.impl.context;

import app.ui.terminal.impl.SingleProcessTerminalInterface;
import command.factory.CommandFactory;

public interface TerminalContext <T extends SingleProcessTerminalInterface> {
    CommandFactory getCommandFactory();
    void printFrame();
    T getOwner();
}
