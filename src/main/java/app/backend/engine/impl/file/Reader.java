package app.backend.engine.impl.file;

import java.io.UncheckedIOException;
import java.nio.file.Path;

public interface Reader {
    String readWhole(Path path) throws UncheckedIOException;
}
