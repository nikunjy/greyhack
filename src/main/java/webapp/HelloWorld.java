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


public class HelloWorld
{
    public static void main(String[] args) throws Exception
    {
    	Server server = new Server();

        SelectChannelConnector connector0 = new SelectChannelConnector();
        connector0.setPort(Integer.parseInt(System.getenv().get("PORT")));
        connector0.setMaxIdleTime(30000);
        connector0.setRequestHeaderSize(8192);

/*        SslSelectChannelConnector ssl_connector = new SslSelectChannelConnector();
        String jetty_home = 
          System.getProperty("jetty.home",".");
        System.setProperty("jetty.home",jetty_home);
        ssl_connector.setPort(Integer.parseInt(System.getenv().get("PORT")));
        ssl_connector.setMaxIdleTime(60000);
	SslContextFactory cf = ssl_connector.getSslContextFactory();
        cf.setKeyStore(jetty_home + "/keystore");
        cf.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
        cf.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");
*/
        server.setConnectors(new Connector[]{connector0});
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new HelloHandler()),"/twitter");
        server.setHandler(context);
        server.start();

    }
}
