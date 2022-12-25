package app.backend.engine.impl.local;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class DateToPathConverterImpl implements DateToPathConverter {
    private Path rootDir;

    public DateToPathConverterImpl(Path rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public Path convert(LocalDate date) {
        String dateRawPath = String.format("%04d/%02d/%02d.json", date.getYear(), date.getMonthValue(),date.getDayOfMonth());
        Path datePath = Paths.get(dateRawPath);
        Path resultPath = rootDir.resolve(datePath);
        return resultPath;
    }
}
