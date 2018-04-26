import java.util.*;
import java.io.*;

public class ParsedDocument extends Document  {
  HashMap<String, Double> tf = new HashMap<String, Double>();
  HashMap<String, Double> idf = new HashMap<String, Double>();
  HashMap<String, Double> tfidf = new HashMap<String, Double>();

	public ParsedDocument(Document doc){
    super(doc);
    tf = TFIDF.generateTF(doc.words);
	}

  public void generateTFIDF(List<String> words, List<ParsedDocument> parsed){
    idf = TFIDF.generateIDF(words, parsed);
    for (String word : tf.keySet()) {
        if(!tfidf.containsKey(word)){
          tfidf.put(word, tf.get(word) * idf.get(word));
        }
    }
  }

  public boolean containsWord(String word){
    return tf.containsKey(word);
  }

  public HashMap<String, Double> getTF(){
    return tf;
  }

  public HashMap<String, Double> getIDF(){
    return idf;
  }

  public HashMap<String, Double> getTFIDF(){
    return tfidf;
  }

  @Override
  public String toString(){
    return super.getFile().getName();
  }

}
