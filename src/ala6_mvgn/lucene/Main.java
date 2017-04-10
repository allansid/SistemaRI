package ala6_mvgn.lucene;

import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.queryparser.classic.ParseException;

public class Main {

	private static String indexDirectoryPath;
	private static String path;
	private static Scanner input = new Scanner(System.in);
	private static boolean stopword = false;
	private static boolean stemming = false;
	private static int totalHits = 0;

	private static String[] paths = {
			"C:\\Users\\Allan\\Desktop\\",
			"C:\\Users\\Allan\\Google Drive\\Mineração da Web ~if796\\Tarefa_1\\result",
			"C:\\Users\\Allan\\Google Drive\\Mineração da Web ~if796\\Tarefa_1\\SistemaRI\\art",
			"C:\\Users\\Milton\\Documents\\Mineração Web\\Tarefa 1\\index",
			"next"
	};	

	public static void createIndex() throws IOException {
		IndexFiles indexer = new IndexFiles(stopword, stemming);
		indexer.indexer(indexDirectoryPath); 
		int numIndexed = indexer.createIndex(path);
		indexer.close();
		System.out.println(numIndexed + " indexed files");
	}

	public static int search(String query) throws IOException, ParseException {

		SearchFiles searcher = new SearchFiles(query, stopword, stemming);
		return searcher.searcher(indexDirectoryPath); 
	}

	public static void pathChoice(String number, boolean aux) {
		if (aux == false) {
			if (number.equals("0"))	{
				path = paths[0];
			} else if (number.equals("2")) {
				path = paths[2];
			} else if (number.equals("4")) {
				path = paths[4];
			}
		} else if (aux == true) {
			if (number.equals("0"))	{
				indexDirectoryPath = paths[0];
			} else if (number.equals("1")) {
				indexDirectoryPath = paths[1];
			} else if (number.equals("3")) {
				indexDirectoryPath = paths[3];	
			}
		}
	}
	
	public static void loadInfo() {
		String temp;
		System.out.println("Enter path: ");
		path = input.nextLine();
		pathChoice(path, false);

		System.out.println("Stopwords? ");
		temp = input.nextLine();
		if (temp.equalsIgnoreCase("sim")) {
			stopword = true;
		}

		System.out.println("Stemming? ");
		temp = input.nextLine();
		if (temp.equalsIgnoreCase("sim")) {
			stopword = true;
		}

		System.out.println("indexDirectoryPath ? ");
		indexDirectoryPath = input.nextLine();		 
		pathChoice(indexDirectoryPath, true);

	}

	public static int nextAction() {
		System.out.println("Choose your next step: ");
		System.out.println("1 - Create a new query");
		System.out.println("2 - Preparate and indexing the documents");
		System.out.println("3 - Quit");

		return input.nextInt();
	}

	public static void calculateRelevance() {
		System.out.println("Enter total relevant documents of base: ");
		int totalDocsOK = input.nextInt();
		System.out.println("Enter number relevant documents found: ");
		int docsOK = input.nextInt();
		System.out.println("Avaliation:");
		double p = (double) docsOK/totalHits;
		double c = (double) docsOK/totalDocsOK;
		double f = (double) 2*p*c/(p+c);

		System.out.println("Precision: " + p);
		System.out.println("Recall: " + c);
		System.out.println("F-measure: " + f);
	}

	public static void main(String[] args) throws IOException, ParseException {
		loadInfo();

		createIndex();

		System.out.println("Enter your query:");
		String query = input.nextLine();

		totalHits = search(query);

		calculateRelevance();

		input.close();
	}

}