package servlets;

import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ShopifyServlet extends HttpServlet {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = "nothing";
        PrintWriter out = resp.getWriter();
        String param1 = "param1";
        String storefrontAccessToken = "428f60c1d1a81e08735df61732c07cbd";
        String baseURL = "https://tystore01.myshopify.com/api/2019-07/graphql";
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("shop ", "{name \n primaryDomain {url\n host\n } }");


        resp.setContentType("application/json; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        if (req.getParameter(param1) != null) {
            String endpoint = req.getParameter(param1);

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(jsonObject.toString(), JSON);
            /*Request request = new Request.Builder()
                    .url(baseURL)
                    .addHeader("X-Shopify-Storefront-Access-Token:", storefrontAccessToken)
                    .addHeader("Content-Type:", "application/graphql")
                    .post(requestBody)
                    .build();
*/
//            RequestBody requestBody1 = RequestBody.create("{shop{name primaryDomain{url host}}}", MediaType.parse("application/json"));

//            System.out.println(requestBody1);
            Request request = new Request.Builder()
                    .url("https://e18f5914ac9300cf6c2ad0d1c1480dc6:e27b0c27814a5a1c3a8f0123f5ca80ca@tystore01.myshopify.com/admin/api/2019-07/products.json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Host", "tystore01.myshopify.com")
                    .get()
                    .build();


            try {
                Response response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                String jsonData = responseBody.string();

                result = response.toString();
//                JSONArray jsonArray = new JSONArray(jsonData);
//                result = jsonArray.get((int)(Math.random()* 9 + 1)).toString();

            } catch (Exception e) {
                result = e.toString();
                System.out.println("Error" + e);
            }
        }

        out.println(result);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
