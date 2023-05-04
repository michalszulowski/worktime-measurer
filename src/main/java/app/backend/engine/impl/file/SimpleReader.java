package app.backend.engine.impl.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SimpleReader implements Reader {
    @Override
    public String readWhole(Path path) throws UncheckedIOException {
        try {
            return readWholeFile(path);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private String readWholeFile(Path path) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        List<String> lines = Files.readAllLines(path);
        lines.forEach(contentBuilder::append);
        return contentBuilder.toString();
    }
}
