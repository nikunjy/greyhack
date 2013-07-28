package api.model;

import java.util.ArrayList;
import java.util.List;

public class PushQuery {
	public String tweetText; 
	public double time; 
	public double lattitude; 
	public double longitude; 
	public List<String> hashTags; 
	public PushQuery() {
		hashTags = new ArrayList<String>();
	}
}
