// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse{
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int frontCodeTick = markdown.indexOf("`", currentIndex);
            int endCodeTick = markdown.indexOf("`", frontCodeTick + 1);
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1){
                return toReturn;
            }
            if(frontCodeTick != -1 && endCodeTick != -1 && !(frontCodeTick > openParen && frontCodeTick < closeParen) && !(frontCodeTick > nextOpenBracket && frontCodeTick < nextCloseBracket)){
                currentIndex = endCodeTick + 1;
                nextOpenBracket = markdown.indexOf("[", currentIndex);
                nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
                openParen = markdown.indexOf("(", nextCloseBracket);
                closeParen = markdown.indexOf(")", openParen);
                if(nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1){
                    return toReturn;
                }
            }

            if((openParen - nextCloseBracket == 1) && (nextOpenBracket == 0 || markdown.indexOf("!", nextOpenBracket-1) != nextOpenBracket-1)){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        //System.out.println(contents);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}