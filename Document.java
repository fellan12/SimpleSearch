import java.util.*;
import java.io.*;

public class Document  {
	public File file;
	public List<String> words = new ArrayList<String>();

	public Document(Document doc){
		this.file = doc.getFile();
		this.words = doc.getWords();
	}

	public Document(File file){
		this.file = file;
		try(Scanner br = new Scanner(file)){
			while(br.hasNext()){
				words.add(br.next());
			}

		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
	}

	public List<String> getWords(){
		return words;
	}
	public File getFile(){
		return file;
	}
}
