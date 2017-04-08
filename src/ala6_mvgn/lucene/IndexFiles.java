package ala6_mvgn.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

// Indexing Process: Document >> Analyzer >> IndexWriter >> Directory


public class IndexFiles {
	
	private static boolean stopword;
	private boolean stemming;
	
	public IndexFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Index document
	private Document getDocument(File file) throws IOException{
		Document doc = new Document();
		
		Field contentField = new Field(null, null, null);
		doc.add(contentField);

		return doc;
	}
	
	public void indexer() throws IOException {
		//Create a IndexWriter
		String indexPath = "index";
		Path p = Paths.get(indexPath);
		Directory d = FSDirectory.open(p);
		Analyzer a; // = new StandardAnalyzer();
		
		if (stopword) {
			String[] stopWordList = {"", "", "", "", ""};
			
		}
		
		if (stemming) {
			a = new EnglishAnalyzer();
		} else {
			a = new StandardAnalyzer();
		}
		
		IndexWriterConfig iwc = new IndexWriterConfig(a);
		IndexWriter iw = new IndexWriter(d, iwc);
		
		//Start Indexing Process
		Document doc = getDocument();
		iw.addDocument(doc);
		
		iw.close();
	}
	
	

}
