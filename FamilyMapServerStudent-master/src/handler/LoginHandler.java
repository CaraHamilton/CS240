package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import request.LoginRequest;
import result.LoginResult;
import service.LoginService;

import java.io.*;
import java.net.HttpURLConnection;

/*Json to request
call service
result to json
return http response
 */

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        try {

            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                System.out.println("in handler1");
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);
                Gson gson = new Gson();
                System.out.println("in handler2");
                LoginRequest loginRequest = gson.fromJson(reqData, LoginRequest.class);

                System.out.println("in handler3");
                LoginService loginService = new LoginService();
                LoginResult loginResult = loginService.login(loginRequest);
                System.out.println("");
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                String jsonRslt = gson.toJson(loginResult);

                OutputStream respBody = exchange.getResponseBody();
                writeString(jsonRslt, respBody);
                respBody.close();
                success = true;

            }
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        } catch(IOException | DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
