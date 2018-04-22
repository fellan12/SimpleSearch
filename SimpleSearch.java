import java.util.*;

class SimpleSearch {
  public static void main(String[] args) {
      String path = args[0];
      String phrase = args[1];

      TFIDFIndexer searcher = new TFIDFIndexer(path);

      HashMap<String, List<ParsedDocument>> invertedIndex = searcher.search(phrase);

      for (Map.Entry<String, List<ParsedDocument>> entry : invertedIndex.entrySet()) {
        System.out.println(entry.getKey()+" : "+entry.getValue());
      }
  }
}
