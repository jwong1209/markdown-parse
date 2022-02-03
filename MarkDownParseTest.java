import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


//javac -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" MarkDownParseTest.java
//java -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore  MarkDownParseTest
public class MarkdownParseTest {
    @Test
    public void addition() throws IOException {
        // assertEquals(2, 1 + 1);

        String contentsTest = Files.readString(Path.of("./test-file.md"));
        String contentsTest1 = Files.readString(Path.of("./test-file2.md"));
        String contentsTest2 = Files.readString(Path.of("./test-file3.md"));
        String contentsTest3 = Files.readString(Path.of("./test-file4.md"));

        assertEquals(List.of("https://something.com", "some-page.html"), 
            MarkdownParse.getLinks(contentsTest));
        assertEquals(List.of("https://try.com", "working.html"), 
            MarkdownParse.getLinks(contentsTest1));
        assertEquals(List.of("https://anything.com", "another-page.html", "another-page.html",
         "a-page.html"), 
            MarkdownParse.getLinks(contentsTest2));
        assertEquals(List.of("link.com"), 
            MarkdownParse.getLinks(contentsTest3));
    }
}