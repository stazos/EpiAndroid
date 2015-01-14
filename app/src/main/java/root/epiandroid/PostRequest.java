package root.epiandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vesy_m on 13/01/15.
 */
public class PostRequest extends AsyncTask<Object, Void, String> {

    private Exception exception;
    private String route;
    private Context context;
    private Map<String, Method> methodMap;

    public PostRequest(Context ctx) {
        context = ctx;
        methodMap = new HashMap<String, Method>();
        try {
            methodMap.put("/login", Controller.class.getDeclaredMethod("login", Context.class, String.class));
            methodMap.put("/info", Controller.class.getDeclaredMethod("info", Context.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    protected String doInBackground(Object... objs) {
        try {
            List<Object> args = new ArrayList();
            for (Object obj : objs) {
                args.add(obj);
            }
            HttpClient httpClient = new DefaultHttpClient();

            route = args.get(0).toString();
            String url = "https://epitech-api.herokuapp.com" + route;
            Log.e("test", url);
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            for (int i = 1; i < args.size(); i = i + 2) {
//                String arg1 = "";
//                String arg2 = "";
//                if (args.get(i) instanceof String) {
//                    arg1 = "\"" + args.get(i) + "\"";
//                } else {
//                    arg1 = args.get(i).toString();
//                }
//                if (args.get(i + 1) instanceof String) {
//                    arg2 = "\"" + args.get(i + 1) + "\"";
//                } else {
//                    arg2 = args.get(i + 1).toString();
//                }
//                Log.e("test", arg1 + " " + arg2);
                nameValuePair.add(new BasicNameValuePair(args.get(i).toString(), args.get(i + 1).toString()));
            }


            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            return responseString;
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String str) {
        try {
            methodMap.get(route).invoke(null, context, str);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

