package app.ui.terminal;

public abstract class AbstractTputFrame implements TerminalFrame {
    protected int consoleWidth;
    protected int consoleHeight;

    public AbstractTputFrame(int consoleWidth, int consoleHeight) {
        this.consoleWidth = consoleWidth;
        this.consoleHeight = consoleHeight;
    }
}
