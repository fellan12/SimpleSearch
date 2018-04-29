# SimpleSearch
A simple search engine that given a set of documents and a search phrase, searches though the documents and returns an inverted index of the search phrase's words and a list of documents sorted by relevance through TF/IDF scoring. Keep in mind that the search phrase is case-sensitive.

## How to use
To run the SimpleSeach application, do as follows:  

Compile:  
`javac *.java` 

Run application:  
`java SimpleSearch {Path to Documents} "Search Phrase"`

## Example  
Documents can be found in the folder: "Avicii"  (Avicii Lyrics)  
Search Phrase: "Hey brother"  

Run:  
`javac *.java`  
`java SimpleSearch ./Avicii "Hey brother"`  

Returns:  
`Hey : [HeyBrother.txt, FriendOfMine.txt]`  
`brother : [HeyBrother.txt, SomewhereInStockholm.txt]`
