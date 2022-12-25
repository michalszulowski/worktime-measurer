package app.ui.terminal.output;

public interface OutStream {
    void print(String s);
    default void println(String s) {
        print(s + "\n");
    }
}
