package webapp;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.xml.XmlConfiguration;


public class EntryPoint
{
	static String API_KEY = "6icbcAXyZx67r8uTAUM5Qw";
	static String API_SECRET = "SCCAdUUc6LXxiazxH3N0QfpNUvlUy84mZ2XZKiv39s";
    public static void main(String[] args) throws Exception
    {
    	Server server = new Server();
        SelectChannelConnector connector0 = new SelectChannelConnector();
        connector0.setPort(Integer.parseInt(System.getenv().get("PORT")));
        connector0.setMaxIdleTime(30000);
        connector0.setRequestHeaderSize(8192);
        server.setConnectors(new Connector[]{connector0});
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new SearchHandler()),"/search");
        context.addServlet(new ServletHolder(new PushHandler()),"/tweet");
        server.setHandler(context);
        server.start();

    }
}
