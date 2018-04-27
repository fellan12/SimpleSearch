import java.util.*;

/**
* Main class of the SimpleSearch
*/
class SimpleSearch {
  public static void main(String[] args) {
      String path = args[0];
      String phrase = args[1];

      System.out.println("Parsing documents...");
      TFIDFIndexer searcher = new TFIDFIndexer(path);
      System.out.println();

      System.out.println("Searching for '"+phrase+"'");
      HashMap<String, List<ParsedDocument>> invertedIndex = searcher.search(phrase);
      System.out.println();

      System.out.println("Printing results as an inverted index:");
      for (Map.Entry<String, List<ParsedDocument>> entry : invertedIndex.entrySet()) {
        System.out.println(entry.getKey()+" : "+entry.getValue());
      }
  }
}
