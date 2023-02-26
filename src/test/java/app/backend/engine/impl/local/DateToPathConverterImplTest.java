package app.backend.engine.impl.local;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateToPathConverterImplTest {
    private static DateToPathConverter dateToPathConverter;
    private static LocalDate dateParam;
    private static Path result;


    @Test
    public void testSimpleDate() {
        LocalDate date = LocalDate.of(1970, 2, 20);
        String userHome = System.getProperty("user.home");
        Path rootDir = Paths.get(userHome);
        givenRootDir(rootDir);
        whenConvertingDate(date);
        thenPathShouldBe(rootDir.resolve(Paths.get("1970/02/20.json")));
    }

    private void givenRootDir(Path rootDir) {
        dateToPathConverter = new DateToPathConverterImpl(rootDir);
    }

    private void whenConvertingDate(LocalDate date) {
        result = dateToPathConverter.convert(date);
    }

    private void thenPathShouldBe(Path expected) {
        assertEquals(expected, result);
    }
}