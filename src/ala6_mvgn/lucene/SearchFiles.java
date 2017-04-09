package ala6_mvgn.lucene;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SearchFiles {
	private boolean stopword;
	private boolean stemming;
	private String query;
	IndexSearcher indexSearch;
	TopDocs hits;
	
	public SearchFiles(String query, boolean stopword, boolean stemming) {
		this.stopword = stopword;
		this.stemming = stemming;
		this.query = query;
	}
	
	private Analyzer getAnalyzer() {
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

	
	public void searcher(String indexDirectoryPath) throws IOException, ParseException {
		Path p = Paths.get(indexDirectoryPath);
		Directory d = FSDirectory.open(p);
		IndexReader ir = DirectoryReader.open(d);
		indexSearch = new IndexSearcher(ir);
	
		Analyzer analyzer = getAnalyzer();

//		PhraseQuery phraseQuery = new PhraseQuery(1,"contents", "julgamento", "deve", "ocorrer");
//		
//		hits = indexSearch.search(phraseQuery, 10);	
		QueryParser queryParser = new QueryParser("contents", analyzer);
		
		Term term = new Term("contents", query); 
		Query termQuery = new TermQuery(term);
		
		Query query = queryParser.parse(this.query);
		
		hits = indexSearch.search(query, 30);	

		printResult();
	}

	public void printResult() throws IOException {
		ScoreDoc[] docs = hits.scoreDocs;
		
		System.out.println("Documents found: "  + docs.length);
		
		for (ScoreDoc scoreDoc : docs) {
			Document doc = indexSearch.doc(scoreDoc.doc);
			System.out.println(doc.getField("fieldname"));
		}
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


	public void setStemming(boolean steamming) {
		this.stemming = steamming;
	}
	
}
