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
					"um", "para", "�", "com", 
					"n�o", "uma", 
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
					"�", 
					"seu", 
					"sua", 
					"ou", 
					"ser", 
					"quando", 
					"muito", 
					"h�", 
					"nos", 
					"j�", 
					"est�", 
					"eu", 
					"tamb�m",
					"s�",
					"pelo", 
					"pela",
					"at�",
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
					"est�o", 
					"voc�", 
					"tinha", 
					"foram", 
					"essa", 
					"num", 
					"nem", 
					"suas", 
					"meu", 
					"�s", 
					"minha", 
					"t�m", 
					"numa", 
					"pelos", 
					"elas", 
					"havia", 
					"seja", 
					"qual", 
					"ser�", 
					"n�s", 
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
					"voc�s", 
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
					"est�",
					"estamos",
					"est�o",
					"estive",
					"esteve",
					"estivemos",
					"estiveram",
					"estava",
					"est�vamos",
					"estavam",
					"estivera",
					"estiv�ramos",
					"esteja",
					"estejamos",
					"estejam",
					"estivesse",
					"estiv�ssemos",
					"estivessem",
					"estiver",
					"estivermos",
					"estiverem",
					"hei",
					"h�",
					"havemos",
					"h�o",
					"houve",
					"houvemos",
					"houveram",
					"houvera",
					"houv�ramos",
					"haja",
					"hajamos",
					"hajam",
					"houvesse",
					"houv�ssemos",
					"houvessem",
					"houver",
					"houvermos",
					"houverem",
					"houverei",
					"houver�",
					"houveremos",
					"houver�o",
					"houveria",
					"houver�amos",
					"houveriam",
					"sou",
					"somos",
					"s�o",
					"era",
					"�ramos",
					"eram",
					"fui",
					"foi",
					"fomos",
					"foram",
					"fora",
					"f�ramos",
					"seja",
					"sejamos",
					"sejam",
					"fosse",
					"f�ssemos",
					"fossem",
					"for",
					"formos",
					"forem",
					"serei",
					"ser�",
					"seremos",
					"ser�o",
					"seria",
					"ser�amos",
					"seriam",
					"tenho",
					"tem",
					"temos",
					"t�m",
					"tinha",
					"t�nhamos",
					"tinham",
					"tive",
					"teve",
					"tivemos",
					"tiveram",
					"tivera",
					"tiv�ramos",
					"tenha",
					"tenhamos",
					"tenham",
					"tivesse",
					"tiv�ssemos",
					"tivessem",
					"tiver",
					"tivermos",
					"tiverem",
					"terei",
					"ter�",
					"teremos",
					"ter�o",
					"teria",
					"ter�amos",
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
