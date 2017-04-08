package ala6_mvgn.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
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




		boolean ignoreCase = true;
		int startSize = 2;
		CharArraySet stopWords = new CharArraySet(startSize, ignoreCase);
		
		if (stopword) { //
			String[] stopWordList = {
					"a", 
					"�", 
					"agora", 
					"ainda", 
					"algu�m", 
					"algum", 
					"alguma", 
					"algumas", 
					"alguns", 
					"ampla", 
					"amplas", 
					"amplo", 
					"amplos", 
					"ante", 
					"antes", 
					"ao", 
					"aos", 
					"ap�s", 
					"aquela", 
					"aquelas", 
					"aquele", 
					"aqueles", 
					"aquilo", 
					"as", 
					"at�", 
					"atrav�s", 
					"cada", 
					"coisa", 
					"coisas", 
					"com", 
					"como", 
					"contra", 
					"contudo", 
					"da", 
					"daquele", 
					"daqueles", 
					"das", 
					"de", 
					"dela", 
					"delas", 
					"dele", 
					"deles", 
					"depois", 
					"dessa", 
					"dessas", 
					"desse", 
					"desses", 
					"desta", 
					"destas", 
					"deste", 
					"deste", 
					"destes", 
					"deve", 
					"devem", 
					"devendo", 
					"dever", 
					"dever�", 
					"dever�o", 
					"deveria", 
					"deveriam", 
					"devia", 
					"deviam", 
					"disse", 
					"disso", 
					"disto", 
					"dito", 
					"diz", 
					"dizem", 
					"do", 
					"dos", 
					"e", 
					"�", 
					"e'", 
					"ela", 
					"elas", 
					"ele", 
					"eles", 
					"em", 
					"enquanto", 
					"entre", 
					"era", 
					"essa", 
					"essas", 
					"esse", 
					"esses", 
					"esta", 
					"est�", 
					"estamos", 
					"est�o", 
					"estas", 
					"estava", 
					"estavam", 
					"est�vamos", 
					"este", 
					"estes", 
					"estou", 
					"eu", 
					"fazendo", 
					"fazer", 
					"feita", 
					"feitas", 
					"feito", 
					"feitos", 
					"foi", 
					"for", 
					"foram", 
					"fosse", 
					"fossem", 
					"grande", 
					"grandes", 
					"h�", 
					"isso", 
					"isto", 
					"j�", 
					"la", 
					"l�", 
					"lhe", 
					"lhes", 
					"lo", 
					"mas", 
					"me", 
					"mesma", 
					"mesmas", 
					"mesmo", 
					"mesmos", 
					"meu", 
					"meus", 
					"minha", 
					"minhas", 
					"muita", 
					"muitas", 
					"muito", 
					"muitos", 
					"na", 
					"n�o", 
					"nas", 
					"nem", 
					"nenhum", 
					"nessa", 
					"nessas", 
					"nesta", 
					"nestas", 
					"ningu�m", 
					"no", 
					"nos", 
					"n�s", 
					"nossa", 
					"nossas", 
					"nosso", 
					"nossos", 
					"num", 
					"numa", 
					"nunca", 
					"o", 
					"os", 
					"ou", 
					"outra", 
					"outras", 
					"outro", 
					"outros", 
					"para", 
					"pela", 
					"pelas", 
					"pelo", 
					"pelos", 
					"pequena", 
					"pequenas", 
					"pequeno", 
					"pequenos", 
					"per", 
					"perante", 
					"pode", 
					"p�de", 
					"podendo", 
					"poder", 
					"poderia", 
					"poderiam", 
					"podia", 
					"podiam", 
					"pois", 
					"por", 
					"por�m", 
					"porque", 
					"porqu�", 
					"posso", 
					"pouca", 
					"poucas", 
					"pouco", 
					"poucos", 
					"primeiro", 
					"primeiros", 
					"pr�pria", 
					"pr�prias", 
					"pr�prio", 
					"pr�prios", 
					"quais", 
					"qual", 
					"quando", 
					"quanto", 
					"quantos", 
					"que", 
					"quem", 
					"s�o", 
					"se", 
					"seja", 
					"sejam", 
					"sem", 
					"sempre", 
					"sendo", 
					"ser�", 
					"ser�o", 
					"seu", 
					"seus", 
					"si", 
					"sido", 
					"s�", 
					"sob", 
					"sobre", 
					"sua", 
					"suas", 
					"talvez", 
					"tamb�m", 
					"tampouco", 
					"te", 
					"tem", 
					"tendo", 
					"tenha", 
					"ter", 
					"teu", 
					"teus", 
					"ti", 
					"tido", 
					"tinha", 
					"tinham", 
					"toda", 
					"todas", 
					"todavia", 
					"todo", 
					"todos", 
					"tu", 
					"tua", 
					"tuas", 
					"tudo", 
					"�ltima", 
					"�ltimas", 
					"�ltimo", 
					"�ltimos", 
					"um", 
					"uma", 
					"umas", 
					"uns", 
					"vendo", 
					"ver", 
					"vez", 
					"vindo", 
					"vir", 
					"vos", 
					"v�s", 
			};

			stopWords.addAll(Arrays.asList(stopWordList));
		} else {
			String[] noStopWordsList = {};
			
			stopWords.addAll(Arrays.asList(noStopWordsList));
		}

		if (stemming) {
			a = new BrazilianAnalyzer(stopWords);
		} else {
			a = new StandardAnalyzer(stopWords);
		}

		IndexWriterConfig iwc = new IndexWriterConfig(a);
		IndexWriter iw = new IndexWriter(d, iwc);

		//Start Indexing Process
		Document doc = getDocument();
		iw.addDocument(doc);

		iw.close();
	}



}
}
