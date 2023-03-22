package app.ui.terminal.output.frame;

import app.ui.terminal.service.TerminalService;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public abstract class AbstractTerminalFrame implements TerminalFrame {
    protected final TerminalService terminalService;

    public AbstractTerminalFrame(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    public TerminalService getTerminalService() {
        return terminalService;
    }

    public int getWidth() {
        return terminalService.getSettings().getSize().getWidth();
    }

    public int getHeight() {
        return terminalService.getSettings().getSize().getHeight();
    }

    protected void fillUpLeftLines() {
        //TODO implement with getting cursor position. Currently only adding 2 lines.
        terminalService.getOutStream().print("\n\n");
    }

    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder().build();
        System.out.println(terminal.getWidth());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(terminal.output()));
        writer.write("Test");
    }
}
