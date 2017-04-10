package ala6_mvgn.lucene;

import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.queryparser.classic.ParseException;

public class Main {

	private static String indexDirectoryPath = "C:\\Users\\Milton\\Documents\\Minera��o Web\\Tarefa 1\\index";
	private static String path;
	private static Scanner input = new Scanner(System.in);
	private static boolean stopword = false;
	private static boolean stemming = false;
	private static int totalHits = 0;

	public static void createIndex() throws IOException {
		IndexFiles indexer = new IndexFiles(stopword, stemming);
		indexer.indexer(indexDirectoryPath); // onde vai salvar os indexados
		int numIndexed = indexer.createIndex(path);
		indexer.close();
		System.out.println(numIndexed + " indexed files");
	}

	public static int search(String query) throws IOException, ParseException {

		SearchFiles searcher = new SearchFiles(query, stopword, stemming);
		return searcher.searcher(indexDirectoryPath); // onde foram salvo os indexados
	}

	public static void loadInfo() {
		String temp;
		System.out.println("Enter path: ");
		path = input.nextLine(); //C:\Users\Allan\Google Drive\doing\Mineração da Web ~if796\Mineração da Web - 2017.1\Tarefa_1\SistemaRI\art\ala6
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
