package app.backend.engine.impl.local;

import java.nio.file.Path;
import java.time.LocalDate;

public interface DateToPathConverter {
    Path convert(LocalDate date);

}
