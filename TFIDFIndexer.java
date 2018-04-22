import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Collectors;
public class TFIDFIndexer  {
	List<ParsedDocument> parsedDocs;

	public TFIDFIndexer(String path) {
		List<Document> docs = new ArrayList<Document>();

		try{
			//Get files
			List<File> filesInFolder = Files.walk(Paths.get(path))
                                .filter(Files::isRegularFile)
                                .map(Path::toFile)
                                .collect(Collectors.toList());

			//Create documents
			for (File f : filesInFolder) {
       docs.add(new Document(f));
      }

			//Parse documents (generate tf, idf, tfidf)
			parsedDocs = parseDocuments(docs);

			//============
			// For testing
			//============
			// for (ParsedDocument pDoc : parsedDocs) {
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

	public HashMap<String, List<ParsedDocument>> search(String phrase){
		HashMap<String, List<ParsedDocument>> invertedIndex = new HashMap<String, List<ParsedDocument>>();
		String[] phraseWords = phrase.split(" ");
		for (String pWord : phraseWords) {
			 List<ParsedDocument> relevanceDocs = new ArrayList<>();
			for (ParsedDocument pDoc : parsedDocs) {
				if(pDoc.getWords().contains(pWord)){
					relevanceDocs.add(pDoc);
				}
			}
			Collections.sort(relevanceDocs, new ParsedDocumentComperator(pWord));
			invertedIndex.put(pWord, relevanceDocs);
		}
		return invertedIndex;
	}

	private List<ParsedDocument> parseDocuments(List<Document> docs){
		List<ParsedDocument> parsed = new ArrayList<ParsedDocument>();
		ParsedDocument parsedDoc;

		for (Document doc : docs ) {
			parsed.add(new ParsedDocument(doc));
		}

		for (ParsedDocument parDoc : parsed) {
			parDoc.generateTFIDF(parDoc.getWords(), parsed);
		}
		return parsed;
	}
}
