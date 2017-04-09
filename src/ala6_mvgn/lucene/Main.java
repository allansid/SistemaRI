package ala6_mvgn.lucene;

import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.queryparser.classic.ParseException;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		boolean stopword = false;
		boolean stemming = false;
		
		String temp;
		System.out.println("Enter path: ");
		String path = input.nextLine();
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
		String indexDirectoryPath = input.nextLine();
		
		IndexFiles indexer = new IndexFiles(stopword, stemming);
		indexer.indexer(indexDirectoryPath); //onde vai salvar os indexados
		int numIndexed = indexer.createIndex(path);
		SearchFiles searcher = new SearchFiles(stopword, stemming);
		searcher.searcher(indexDirectoryPath); //onde foram salvo os indexados
		
		System.out.println(numIndexed+" indexed files");
		
	}
}
