package app.ui.terminal.output;

public class StringBuilderOutStream implements OutStream {
    private StringBuilder sBuilder;

    public StringBuilderOutStream() {
        sBuilder = new StringBuilder();
    }

    @Override
    public void print(String s) {
        sBuilder.append(s);
    }

    public String getContent() {
        return sBuilder.toString();
    }

    public void clear() {
        sBuilder = new StringBuilder();
    }
}
