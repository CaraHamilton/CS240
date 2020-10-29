package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import dao.DataAccessException;
import result.ClearResult;
import service.ClearService;

import java.io.*;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                ClearService clearService = new ClearService();
                ClearResult clearResult = clearService.clear();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                Gson gson = new Gson();
                String jsonRslt = gson.toJson(clearResult);

                OutputStream respBody = exchange.getResponseBody();
                writeString(jsonRslt, respBody);
                respBody.close();
                success = true;
            }
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        } catch (DataAccessException | IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
