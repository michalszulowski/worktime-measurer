package app.ui.terminal.service;

import app.ui.terminal.output.OutStream;
import app.ui.terminal.output.TerminalOutStream;

import java.io.IOException;
import java.io.UncheckedIOException;

public abstract class LinuxTerminalService implements TerminalService {
    @Override
    public void clear() {
        //TODO think about moving to system module
        /*
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
         */
        //TODO clear the above. That approach does not work, not really sure why.
        getOutStream().print("\033[H\033[2J");
    }

    @Override
    public OutStream getOutStream() {
        return new TerminalOutStream();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //TODO it does not work for some reason
        System.out.println("TEST");
        Thread.sleep(2000);
        Runtime.getRuntime().exec("clear");
    }
}
