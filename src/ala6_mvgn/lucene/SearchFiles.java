package ala6_mvgn.lucene;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SearchFiles {
	private boolean stopword;
	private boolean stemming;
	IndexSearcher indexSearch;
	
	public SearchFiles(boolean stopword, boolean stemming, Query query) {
		this.stopword = stopword;
		this.stemming = stemming;
	}
	
	public void Searcher() throws IOException, ParseException {
		String indexPath = "index";
		Path p = Paths.get(indexPath);
		Directory d = FSDirectory.open(p);
		IndexReader ir = DirectoryReader.open(d);
		indexSearch = new IndexSearcher(ir);
	
		Analyz aux = new Analyz(stopword, stemming);
		Analyzer a = aux.getAnalyzer();

		String field = "";
		QueryParser queryParser = new QueryParser(field, a);
		
//		Term term = new Term("contents", "tema");
		Query query = queryParser.parse(null);
		
		TopDocs temp = indexSearch.search(query, 30);
		

	}
	

	public static void main(String[] args) throws Exception {
				
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
