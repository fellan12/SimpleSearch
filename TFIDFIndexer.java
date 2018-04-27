import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.Collectors;

/**
* A class that indexes a set of documents for TF-IDF searching
*
* @author Felix
*/
public class TFIDFIndexer  {
	List<ParsedDocument> docs;

	/**
	* Constructor that indexes all the documents given a path
	*
	* @param path - Path to the documents
	*/
	public TFIDFIndexer(String path) {
		docs = new ArrayList<ParsedDocument>();

		try{
			//Get files
			List<File> filesInFolder = Files.walk(Paths.get(path))
                                .filter(Files::isRegularFile)
                                .map(Path::toFile)
                                .collect(Collectors.toList());

			//Create documents
			for (File f : filesInFolder) {
       docs.add(new ParsedDocument(f));
      }

			//Parse documents (generate tf, idf, tfidf)
			parseDocuments(docs);

			//============
			// For testing
			//============
			// for (ParsedDocument pDoc : docs) {
			// 	System.out.println(pDoc.getFile().getName());
			// 	System.out.println("TF");
			// 	for (Map.Entry<String, Double> entry : pDoc.getTF().entrySet()) {
			// 		System.out.println(entry.getKey()+" : "+entry.getValue());
			// 	}
			// 	System.out.println("IDF");
			// 	for (Map.Entry<String, Double> entry : pDoc.getIDF().entrySet()) {
			// 		System.out.println(entry.getKey()+" : "+entry.getValue());
			// 	}
			// 	System.out.println("TFIDF");
			// 	for (Map.Entry<String, Double> entry : pDoc.getTFIDF().entrySet()) {
			// 		System.out.println(entry.getKey()+" : "+entry.getValue());
			// 	}
			// 	System.out.println();
			// }

		} catch (IOException ex){
			ex.printStackTrace();
		}
	}

	/**
	* Parses the documents and fills them with a TF-map, IDF-map and TFIDF-map
	* given the information of the documents
	*
	* @param docs - a list of documents
	* @return a list of parsed documents
	*/
	private void parseDocuments(List<ParsedDocument> docs){
		List<ParsedDocument> parsed = new ArrayList<ParsedDocument>();

		//Set TF
		for (ParsedDocument doc : docs) {
			doc.setTF(TFIDFGenerator.generateTF(doc.getWords()));
		}

		//Set IDF and TFIDF
		for (ParsedDocument doc : docs) {
			doc.setIDF(TFIDFGenerator.generateIDF(doc.getWords(), docs));
			doc.setTFIDF(TFIDFGenerator.generateTFIDF(doc, docs));
		}
	}

	/**
	* Searchs for the phrase in the indexes documents
	*
	* @param phrase - Search phrase
	* @return a Hashmap containing the phrases words mapped to a list of documents
	*/
	public HashMap<String, List<ParsedDocument>> search(String phrase){
		HashMap<String, List<ParsedDocument>> invertedIndex = new HashMap<String, List<ParsedDocument>>();
		String[] phraseWords = phrase.replaceAll("[^a-zA-Z ]", "").split("\\s+");
		for (String pWord : phraseWords) {
			 List<ParsedDocument> relevanceDocs = new ArrayList<>();
			for (ParsedDocument pDoc : docs) {
				if(pDoc.getWords().contains(pWord)){
					relevanceDocs.add(pDoc);
				}
			}
			//Sort relevant documents by TF-IDF scoring
			Collections.sort(relevanceDocs, new ParsedDocumentComperator(pWord));
			invertedIndex.put(pWord, relevanceDocs);
		}
		return invertedIndex;
	}


}
