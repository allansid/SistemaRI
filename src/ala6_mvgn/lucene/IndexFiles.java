package ala6_mvgn.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

// Indexing Process: Document >> Analyzer >> IndexWriter >> Directory
public class IndexFiles {

	private boolean stopword;
	private boolean stemming;
	private IndexWriter writer;

	public IndexFiles(boolean stopword, boolean stemming) {
		this.stemming = stemming;
		this.stopword = stopword;
	}
	
	private Analyzer getAnalyzer(boolean stopword, boolean stemming) {
		Analyzer analyzer = null;
		
		if(stemming) {
			if(stopword) {
				analyzer = new BrazilianAnalyzer();
			} else {
				analyzer = new BrazilianAnalyzer(new CharArraySet(Collections.emptyList(), true));
			}
			
		} else {
			if(stopword) {
				analyzer = new StandardAnalyzer(new BrazilianAnalyzer().getStopwordSet());
			} else {
				analyzer = new StandardAnalyzer(new CharArraySet(Collections.emptyList(), true));
			}
		}
		
		return analyzer;
	}

	// Index document
	private Document getDocument(File file) throws IOException{
		Document doc = new Document();

		// index file contents
		Field contentField = new Field("contents", new FileReader(file), TextField.TYPE_NOT_STORED);
		// index file name
		Field fileNameField = new Field("fieldname",file.getName(), TextField.TYPE_STORED);
		// index file path
		Field filePathField = new Field("filepath", file.getCanonicalPath(), TextField.TYPE_STORED);

		doc.add(contentField);
		doc.add(fileNameField);
		doc.add(filePathField);

		return doc;
	}

	public void indexer(String indexDirectoryPath) throws IOException {
		//Create a IndexWriter
		Path p = Paths.get(indexDirectoryPath);
		try {
			Directory d = FSDirectory.open(p);
			
			Analyzer analyzer = getAnalyzer(stopword, stemming);
			
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			writer = new IndexWriter(d, iwc);
		} catch (IOException e) {			
			e.printStackTrace();
		}

	}
	
	public void close() throws IOException {
		writer.close();
	}

	
	public int createIndex(String FilesPath) throws IOException {
		// Pega todos os arquivos do diretorio
		File[] files = new File(FilesPath).listFiles();

		for (File file : files) {
			if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead()) {
				//Start Indexing Process
				Document document = getDocument(file);
				writer.addDocument(document);
			}
		}
		return writer.numDocs();
	}

	
	public boolean isStopword() {
		return stopword;
	}

	public void setStopword(boolean stopword) {
		this.stopword = stopword;
	}

	public boolean isStemming() {
		return stemming;
	}

	public void setStemming(boolean stemming) {
		this.stemming = stemming;
	}

	public IndexWriter getWriter() {
		return writer;
	}

	public void setWriter(IndexWriter writer) {
		this.writer = writer;
	}


}
