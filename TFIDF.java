import java.util.*;

public class TFIDF {
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

}
