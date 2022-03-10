import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class WordCountVisitor extends AbstractVisitor {
    int wordCount = 0;

    @Override
    public void visit(Text text) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        wordCount += text.getLiteral().split("\\W+").length;

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(text);
    }
}

class LinkVisitor extends AbstractVisitor {
    int wordCount = 0;

    @Override
    public void visit(Link text) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        //wordCount += text.getLiteral().split("\\W+").length;

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        //wordCount += text.getDestination().split("\\W+").length;
        ArrayList<String> container = new ArrayList<>();
        String links = text.getDestination();
        String[] linkArray = links.split("\n");
        for(int i = 0; i < linkArray.length; i++){
            container.add(linkArray[i]);
        }
        visitChildren(text);
        System.out.println(container);
    } 
}

class TryCommonMark {
    public static void main(String[] args) throws IOException{
        Parser parser = Parser.builder().build();
        Node document = parser.parse("This is *Sparta*");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        //renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        System.out.println(renderer.render(document));

        System.out.println("____________________________");
        //# this part actually does the computation
        Node node = parser.parse("Example\n=======\n\nSome more text");
        WordCountVisitor visitor = new WordCountVisitor();
        node.accept(visitor);
        System.out.println(visitor.wordCount);  // 4

        System.out.println("-------------------------------");
        Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        //System.out.println(contents);
        Node linkNode = parser.parse(contents);
        LinkVisitor linkGet = new LinkVisitor();
        linkNode.accept(linkGet);
    }
}