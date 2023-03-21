package app.ui.terminal.output.frame;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public abstract class JLineTerminalFrame implements TerminalFrame {
    protected final Terminal terminal = null; //TODO pass somehow
    protected int width; //TODO think about whole TerminalSettings thing
    protected int height;

    public JLineTerminalFrame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    protected void fillUpLeftLines() {


        //TODO implement
    }

    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder().dumb(true)
                .build();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(terminal.output()));
        writer.write("Test");
    }
}
