package app.backend.engine.impl.file;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleWriter implements Writer {
    @Override
    public void writeWhole(Path to, String content) {
        try {
            tryToWriteWholeFile(to, content);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private void tryToWriteWholeFile(Path to, String content) throws IOException {
        //TOOO figure out if file is created if not existed
        List<String> contentLines = content.lines().collect(Collectors.toList());
        Files.write(to, contentLines, StandardCharsets.UTF_8);
    }
}
