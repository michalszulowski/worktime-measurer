package app.backend.engine.impl.file;

import java.nio.file.Path;

public interface Writer {
    void writeWhole(Path to, String content);
}
