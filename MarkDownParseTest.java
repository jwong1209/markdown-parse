import static org.junit.Assert.*;
import org.junit.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.io.IOException;

public class MarkDownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testFileOne() throws IOException{

        String contentsTest = Files.readString(Path.of("test-file.md"));
        assertEquals(List.of("https://something.com", "some-page.html"),
                MarkDownParse.getLinks(contentsTest));
    }

    @Test
    public void testProfessorFile6() throws IOException{
        String contentsTest = Files.readString(Path.of("professorFile6.md"));
        assertEquals(List.of("https://something.com","some-page.html"),
                MarkDownParse.getLinks(contentsTest));
    }
}