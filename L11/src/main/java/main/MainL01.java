package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.MirrorServlet;
import servlets.ShopifyServlet;

public class MainL01 {
    public static void main(String[] args) throws Exception {
        MirrorServlet l01MirrorServlet = new MirrorServlet();
        ShopifyServlet shopifyServlet = new ShopifyServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(l01MirrorServlet), "/mirror");
        context.addServlet(new ServletHolder(shopifyServlet ), "/shopify");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();



    }
}
