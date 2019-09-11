package servlets;

//import templater.PageGenerator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class MirrorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String result = "";
        PrintWriter out = resp.getWriter();

        resp.setContentType("text/html; charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        if (req.getParameter("key") != null) {
            String endpoint = req.getParameter("key");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/" + endpoint)
                    .get()
                    .build();
            try {
                Response response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                String jsonData = responseBody.string();

                JSONArray jsonArray = new JSONArray(jsonData);
                result = jsonArray.get((int)(Math.random()* 9 + 1)).toString();

            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }

        out.println(result);


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
