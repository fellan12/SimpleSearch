import java.util.Comparator;

public class ParsedDocumentComperator implements Comparator<ParsedDocument> {

    private final String sortWord;

    public ParsedDocumentComperator (String word) {
        this.sortWord = word;
    }

    //Sort in a decending order
    public int compare(ParsedDocument pDoc1, ParsedDocument pDoc2) {
        if (pDoc1.getTFIDF().get(sortWord) < pDoc2.getTFIDF().get(sortWord)) return 1;
        if (pDoc1.getTFIDF().get(sortWord) > pDoc2.getTFIDF().get(sortWord)) return -1;
        return 0;
    }

}
