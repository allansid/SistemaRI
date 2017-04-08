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

		if (stopword) { //
			String[] stopWordList = {"de", "a", "o", "que", "e", "do", "da", "em", 
					"um", "para", "é", "com", 
					"não", "uma", 
					"os", "no",
					"se", "na",
					"por",
					"mais",
					"as",
					"dos",
					"como", 
					"mas",
					"foi",
					"ao",
					"ele",
					"das",
					"tem", 
					"à", 
					"seu", 
					"sua", 
					"ou", 
					"ser", 
					"quando", 
					"muito", 
					"há", 
					"nos", 
					"já", 
					"está", 
					"eu", 
					"também",
					"só",
					"pelo", 
					"pela",
					"até",
					"isso",
					"ela",
					"entre", 
					"era",
					"depois", 
					"sem",
					"mesmo", 
					"aos",
					"ter", 
					"seus", 
					"quem", 
					"nas", 
					"me", 
					"esse", 
					"eles", 
					"estão", 
					"você", 
					"tinha", 
					"foram", 
					"essa", 
					"num", 
					"nem", 
					"suas", 
					"meu", 
					"às", 
					"minha", 
					"têm", 
					"numa", 
					"pelos", 
					"elas", 
					"havia", 
					"seja", 
					"qual", 
					"será", 
					"nós", 
					"tenho", 
					"lhe", 
					"deles", 
					"essas", 
					"esses", 
					"pelas", 
					"este", 
					"fosse", 
					"dele", 
					"tu", 
					"te", 
					"vocês", 
					"vos", 
					"lhes", 
					"meus", 
					"minhas",
					"teu",
					"tua",
					"teus",
					"tuas",
					"nosso", 
					"nossa",
					"nossos",
					"nossas",
					"dela", 
					"delas", 
					"esta", 
					"estes", 
					"estas", 
					"aquele", 
					"aquela",
					"aqueles",
					"aquelas",  
					"isto",
					"aquilo",
					"estou",
					"está",
					"estamos",
					"estão",
					"estive",
					"esteve",
					"estivemos",
					"estiveram",
					"estava",
					"estávamos",
					"estavam",
					"estivera",
					"estivéramos",
					"esteja",
					"estejamos",
					"estejam",
					"estivesse",
					"estivéssemos",
					"estivessem",
					"estiver",
					"estivermos",
					"estiverem",
					"hei",
					"há",
					"havemos",
					"hão",
					"houve",
					"houvemos",
					"houveram",
					"houvera",
					"houvéramos",
					"haja",
					"hajamos",
					"hajam",
					"houvesse",
					"houvéssemos",
					"houvessem",
					"houver",
					"houvermos",
					"houverem",
					"houverei",
					"houverá",
					"houveremos",
					"houverão",
					"houveria",
					"houveríamos",
					"houveriam",
					"sou",
					"somos",
					"são",
					"era",
					"éramos",
					"eram",
					"fui",
					"foi",
					"fomos",
					"foram",
					"fora",
					"fôramos",
					"seja",
					"sejamos",
					"sejam",
					"fosse",
					"fôssemos",
					"fossem",
					"for",
					"formos",
					"forem",
					"serei",
					"será",
					"seremos",
					"serão",
					"seria",
					"seríamos",
					"seriam",
					"tenho",
					"tem",
					"temos",
					"tém",
					"tinha",
					"tínhamos",
					"tinham",
					"tive",
					"teve",
					"tivemos",
					"tiveram",
					"tivera",
					"tivéramos",
					"tenha",
					"tenhamos",
					"tenham",
					"tivesse",
					"tivéssemos",
					"tivessem",
					"tiver",
					"tivermos",
					"tiverem",
					"terei",
					"terá",
					"teremos",
					"terão",
					"teria",
					"teríamos",
					"teriam};"

			};

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
}
