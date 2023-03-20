package app.ui.terminal.input;

import java.io.*;

public class FromStreamBufferedReader implements InputReader {
    private final InputStream sourceStream;
    private final BufferedReader bufferedReader;

    public FromStreamBufferedReader(InputStream sourceStream) {
        this.sourceStream = sourceStream;
        bufferedReader = new BufferedReader(new InputStreamReader(this.sourceStream));
    }

    @Override
    public String waitForAndRead() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
