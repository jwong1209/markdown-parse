import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//ssh cs15lwi22zz@ieng6.ucsd.edu
//javac -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" MarkdownParseTest.java
//java -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MarkdownParseTest
public class MarkdownParseTest {
    @Test
    public void addition() throws IOException {
        // assertEquals(2, 1 + 1);

        String contentsTest = Files.readString(Path.of("test-file.md"));
        String contentsTest1 = Files.readString(Path.of("test-file2.md"));
        String contentsTest2 = Files.readString(Path.of("test-file3.md"));
        String contentsTest3 = Files.readString(Path.of("test-file4.md"));

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

    @Test
    public void testSnippet1() throws IOException{
        String contents = Files.readString(Path.of("snippet1.md"));
        List<String> expect = List.of("`google.com","google.com", "ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet2() throws IOException{
        String contents = Files.readString(Path.of("snippet2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet3() throws IOException{
        String contents = Files.readString(Path.of("snippet3.md"));
        List<String> expect = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
}
