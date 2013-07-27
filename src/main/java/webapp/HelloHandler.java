package webapp;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class HelloHandler extends HttpServlet { 
	static String API_KEY = "6icbcAXyZx67r8uTAUM5Qw";
	static String API_SECRET = "SCCAdUUc6LXxiazxH3N0QfpNUvlUy84mZ2XZKiv39s";
	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/statuses/update.json";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		OAuthService service = new ServiceBuilder()
        .provider(TwitterApi.class)
        .apiKey(API_KEY)
        .apiSecret(API_SECRET)
        .build();
		
		/*Token requestToken = service.getRequestToken();
		String authUrl = service.getAuthorizationUrl(requestToken);
		System.out.println(authUrl); 
		Scanner in = new Scanner(System.in);
		Verifier v = new Verifier(in.nextLine());
		Token accessToken = service.getAccessToken(requestToken,v);
		System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();
*/
	    // Now let's go and ask for a protected resource!
	    System.out.println("Now we're going to access a protected resource...");
	    Token accessToken = new Token("86719044-ei6BThAF9AZO1lKJVrrA1xmyMUrqHM3r39sw6XLQo","ohb3cQKBEteNUbJnA9Bg8gVQitdjS3NyYH84XT0otY");
	    String query = PROTECTED_RESOURCE_URL+"?status=coordinatetest&lat="+String.valueOf(request.getParameter("lat"))+"&long="+String.valueOf(request.getParameter("long"));
	    OAuthRequest scribeRequest = new OAuthRequest(Verb.POST, PROTECTED_RESOURCE_URL);
	    scribeRequest.addBodyParameter("status", request.getParameter("status"));
	    System.out.println(request.getParameter("lat"));
	    System.out.println(request.getParameter("long"));
	    //scribeRequest.addBodyParameter("place_id","df51dec6f4ee2b2c");
	    scribeRequest.addBodyParameter("lat",String.valueOf(request.getParameter("lat")));
	    scribeRequest.addBodyParameter("long",String.valueOf(request.getParameter("long")));
	    System.out.println(scribeRequest.getBodyContents());
	    
	    service.signRequest(accessToken, scribeRequest);
	    Response scribeResponse = scribeRequest.send();
	    response.getWriter().println(scribeResponse.getBody());

	}
}