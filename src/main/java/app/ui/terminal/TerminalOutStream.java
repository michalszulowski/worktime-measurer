package app.ui.terminal;

public class TerminalOutStream implements OutStream {
    @Override
    public void print(String s) {
        System.out.print(s);
    }
}
