package app.backend.engine.impl.local.json;

import app.backend.engine.impl.local.DateToPathConverter;

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
        String dateRawPath = String.format("/%04d/%02d/%02d.json", date.getYear(), date.getMonthValue(),date.getDayOfMonth());
        Path datePath = Paths.get(dateRawPath);
        return rootDir.relativize(datePath);
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(1970, 2, 20);
        Path rootDir = Paths.get("/home/michal/Desktop/");
        DateToPathConverter converter = new DateToPathConverterImpl(rootDir);
        System.out.println(converter.convert(date));
    }
}
