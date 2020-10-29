package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class FileHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange httpExchange) throws IOException{

        boolean success = false;
        try {
            //only take get requests
            if (httpExchange.getRequestMethod().toLowerCase().equals("get")) {
                String urlPath = httpExchange.getRequestURI().toString();
                String newUrlPath;
                //if urlPath is "/", set to "/index.html"
                if (urlPath.equals("/")) {
                    newUrlPath = "web/index.html";
                }
                else {
                    newUrlPath = "web" + urlPath;
                }
                File file = new File(newUrlPath);
                if (file.exists()) {
                    httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream respBody = httpExchange.getResponseBody();
                    Files.copy(file.toPath(), respBody);
                    httpExchange.getResponseBody().close();
                    success = true;
                }
            }

            if (!success) {
                String filePath = "web/HTML/404.html";
                File file = new File(filePath);
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                OutputStream respBody = httpExchange.getResponseBody();
                Files.copy(file.toPath(), respBody);
                httpExchange.getResponseBody().close();
            }
        } catch (IOException e) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            httpExchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
