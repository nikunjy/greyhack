package webapp;

import java.io.IOException;

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

public class PushHandler  extends HttpServlet {
	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/statuses/update.json";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println(request.getParameter("query"));
		OAuthService service = new ServiceBuilder()
        .provider(TwitterApi.class)
        .apiKey(EntryPoint.API_KEY)
        .apiSecret(EntryPoint.API_SECRET)
        .build();
		Token accessToken = new Token("86719044-ei6BThAF9AZO1lKJVrrA1xmyMUrqHM3r39sw6XLQo","ohb3cQKBEteNUbJnA9Bg8gVQitdjS3NyYH84XT0otY");
		OAuthRequest scribeRequest = new OAuthRequest(Verb.POST, PROTECTED_RESOURCE_URL);
		scribeRequest.addBodyParameter("status", request.getParameter("status"));
	    //scribeRequest.addBodyParameter("place_id","df51dec6f4ee2b2c");
	    scribeRequest.addBodyParameter("lat",String.valueOf(request.getParameter("lat")));
	    scribeRequest.addBodyParameter("long",String.valueOf(request.getParameter("long")));
	    System.out.println(scribeRequest.getBodyContents());
	    service.signRequest(accessToken, scribeRequest);
	    Response scribeResponse = scribeRequest.send();
	    response.getWriter().println(scribeResponse.getBody());
		
	}
}
