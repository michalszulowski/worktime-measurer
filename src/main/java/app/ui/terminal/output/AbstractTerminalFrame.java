package app.ui.terminal.output;

public abstract class AbstractTerminalFrame implements TerminalFrame {
    protected int width;
    protected int height;

    public AbstractTerminalFrame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
