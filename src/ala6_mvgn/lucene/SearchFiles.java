package ala6_mvgn.lucene;

public class SearchFiles {
	private boolean stopword;
	private boolean stemming;
	
	
	public SearchFiles(boolean stopword, boolean stemming) {
		this.stopword = stopword;
		this.stemming = stemming;
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
