package api.model;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
	public String userName;
	public int userId; 
	public int tweetId;
	public String image_url; 
	public double lattitude; 
	public double longitude; 
	public String statusText;
	public double statusTime;
	public List<String> hashTags; 
	public String statusPlaceName; 
	Tweet() { 
		hashTags = new ArrayList<String>();
	}
}
