import java.util.*;

/**
 *  A class that generates TF and IDF
 *
 * @author Felix De Silva
 */
public class TFIDFGenerator {

   /**
   * Generates the term frequency given a list of words.
   *
   * @param words - List of words
   * @return a HashMap containing the terms frequency
   */
    public static HashMap<String, Double> generateTF(List<String> words) {
      HashMap<String, Double> tf = new HashMap<String, Double>();
      for (String word : words) {
        if(!tf.containsKey(word)){
          tf.put(word, 1.0);
        } else {
          tf.put(word, tf.get(word)+1.0);
        }
      }
      return tf;
    }

    /**
    * Generates the inverse document frequency from a list of documents given a list of words.
    *
    * @param words - List of words
    * @param docs - List of documents
    * @return a HashMap contaings the frequency of term in all the documents
    */
    public static HashMap<String, Double> generateIDF(List<String> words, List<ParsedDocument> docs) {
      HashMap<String, Double> idf = new HashMap<String, Double>();
        for (String word : words) {
          double numOfDocs = 0;
          for (ParsedDocument pDoc : docs) {
            if(pDoc.getTF().containsKey(word)){
              numOfDocs++;
            }
          }

          if(numOfDocs != 0){
              idf.put(word, Math.log(docs.size() / numOfDocs));
          } else {
            idf.put(word, 0.0);
          }
        }

        return idf;
    }

    /**
    * Generates the term frequency-inverse document frequency from a list of parsed documents given a list of words.
    *
    * @param words - List of words
    * @param parsed - List of parsed documents
    * @return a HashMap contaings the TFIDF scores
    */
    public static HashMap<String, Double> generateTFIDF(ParsedDocument doc, List<ParsedDocument> parsed){
      HashMap<String, Double> tfidf = new HashMap<String, Double>();
      for (String word : doc.getTF().keySet()) {
          if(doc.containsWord(word)){
            tfidf.put(word, doc.getTF().get(word) * doc.getIDF().get(word));
          }
      }
      return tfidf;
    }

}
