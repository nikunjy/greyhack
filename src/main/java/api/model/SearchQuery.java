package api.model;

import java.util.ArrayList;
import java.util.List;

public class SearchQuery {
	public double lattitude; 
	public double longitude;
	public String businessName;
	public double timeRange; 
	public String text; 
	public List<String> hashTags; 
	public SearchQuery() { 
		hashTags = new ArrayList<String>(); 
	}
}
