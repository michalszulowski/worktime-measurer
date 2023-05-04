package app.backend.engine.impl.file;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SimpleReaderTest {

    @Test
    public void testReading() {
        //given
        SimpleReader reader = new SimpleReader();

        //when
        URL resource = SimpleReader.class.getResource("read_test_file.txt");
        Path testFile = null;
        try {
            testFile = Paths.get(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String content = reader.readWhole(testFile);

        //then
        String expectedContent = "abc\n" +
                "def\n" +
                "ghi\n" +
                "\n" +
                "\n" +
                "jkl";
        assertEquals(expectedContent, content);
    }

}