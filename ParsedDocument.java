import java.util.*;
import java.io.*;

/**
* a class representing a document
*/
public class ParsedDocument {
  private File file;
	private List<String> words = new ArrayList<String>();
  private HashMap<String, Double> tf = new HashMap<String, Double>();
  private HashMap<String, Double> idf = new HashMap<String, Double>();
  private HashMap<String, Double> tfidf = new HashMap<String, Double>();

  /**
  * Adding the document with its Term frequency
  *
  * @param doc - Document to be parsed
  */
	public ParsedDocument(File file){
		this.file = file;
		try(Scanner br = new Scanner(file)){
			while(br.hasNext()){
				words.add(br.next().replaceAll("[^a-zA-Z ]", ""));
			}

		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
	}

  public boolean containsWord(String word){
    return tf.containsKey(word);
  }

  public HashMap<String, Double> getTF(){
    return tf;
  }

  public void setTF(HashMap<String, Double> tf){
    this.tf = tf;
  }

  public HashMap<String, Double> getIDF(){
    return idf;
  }

  public void setIDF(HashMap<String, Double> idf){
    this.idf = idf;
  }

  public HashMap<String, Double> getTFIDF(){
    return tfidf;
  }

  public void setTFIDF(HashMap<String, Double> tfidf){
    this.tfidf = tfidf;
  }

  public List<String> getWords(){
		return words;
	}

  public File getFile(){
    return file;
  }

  @Override
  public String toString(){
    return this.file.getName();
  }

}
