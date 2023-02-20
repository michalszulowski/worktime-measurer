package app.backend.engine.impl.local;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//TODO refactor
class DateToPathConverterImplTest {
    @Test
    public void testIfPathCorrect() {
        LocalDate date = LocalDate.of(1970, 2, 20);
        String userHome = System.getProperty("user.home");
        Path rootDir = Paths.get(userHome);
        DateToPathConverter converter = new DateToPathConverterImpl(rootDir);
        final String EXPECTED_PATH = userHome + "/1970/02/20.json";
        Path resultPath = converter.convert(date);
        assertEquals(resultPath.toString(), EXPECTED_PATH);
    }
}