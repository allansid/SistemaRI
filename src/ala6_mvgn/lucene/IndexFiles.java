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
					"à", 
					"agora", 
					"ainda", 
					"alguém", 
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
					"após", 
					"aquela", 
					"aquelas", 
					"aquele", 
					"aqueles", 
					"aquilo", 
					"as", 
					"até", 
					"através", 
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
					"deverá", 
					"deverão", 
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
					"é", 
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
					"está", 
					"estamos", 
					"estão", 
					"estas", 
					"estava", 
					"estavam", 
					"estávamos", 
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
					"há", 
					"isso", 
					"isto", 
					"já", 
					"la", 
					"lá", 
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
					"não", 
					"nas", 
					"nem", 
					"nenhum", 
					"nessa", 
					"nessas", 
					"nesta", 
					"nestas", 
					"ninguém", 
					"no", 
					"nos", 
					"nós", 
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
					"pôde", 
					"podendo", 
					"poder", 
					"poderia", 
					"poderiam", 
					"podia", 
					"podiam", 
					"pois", 
					"por", 
					"porém", 
					"porque", 
					"porquê", 
					"posso", 
					"pouca", 
					"poucas", 
					"pouco", 
					"poucos", 
					"primeiro", 
					"primeiros", 
					"própria", 
					"próprias", 
					"próprio", 
					"próprios", 
					"quais", 
					"qual", 
					"quando", 
					"quanto", 
					"quantos", 
					"que", 
					"quem", 
					"são", 
					"se", 
					"seja", 
					"sejam", 
					"sem", 
					"sempre", 
					"sendo", 
					"será", 
					"serão", 
					"seu", 
					"seus", 
					"si", 
					"sido", 
					"só", 
					"sob", 
					"sobre", 
					"sua", 
					"suas", 
					"talvez", 
					"também", 
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
					"última", 
					"últimas", 
					"último", 
					"últimos", 
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
					"vós", 
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
