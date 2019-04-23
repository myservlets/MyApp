package seven.handler;

import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import okhttp3.*;
import seven.team.util.MyApplication;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServletsConn {
    //public static String host = "http://243i4s6955.zicp.vip/test/";
    //public static String host = "http://243i4s6955.zicp.vip/MyServlets_war_exploded/";
    public static String host = "http://www.whuyjs.club:8080/test/";
    //public static String host = "http://10.135.5.232:8080/test/";
    //      static String host = "http://192.168.137.1:8080/";

    public static final String TAG = "MainActivity";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String connServlets(String url, String json) {
        String json1= null;
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(host + url)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                json1 = response.body().string();
                Log.println(Log.VERBOSE,url,json1);
                response.body().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json1;
    }
}