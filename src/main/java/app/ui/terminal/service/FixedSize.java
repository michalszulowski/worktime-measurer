package app.ui.terminal.service;

public class FixedSize implements TerminalSize {
    private final int width;
    private final int height;

    public FixedSize(int width, int height) {
        this.width = width;
        this.height = height;
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
