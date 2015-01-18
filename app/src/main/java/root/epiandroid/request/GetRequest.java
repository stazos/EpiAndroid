package root.epiandroid.request;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import root.epiandroid.controller.RequestController;

/**
 * Created by vesy_m on 13/01/15.
 */
public class GetRequest extends AsyncTask<Object, Void, String> {

    private Exception exception;
    private String route;
    private Context context;
    private Map<String, Method> methodMap;

    public GetRequest(Context ctx) {
        context = ctx;
        methodMap = new HashMap<String, Method>();
        try {
            methodMap.put("/photo", RequestController.class.getDeclaredMethod("getPhoto", Context.class, String.class));
            methodMap.put("/messages", RequestController.class.getDeclaredMethod("getMessages", Context.class, String.class));
            methodMap.put("/planning", RequestController.class.getDeclaredMethod("getPlanning", Context.class, String.class));
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
            String url = "https://epitech-api.herokuapp.com" + route + "?";
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            for (int i = 1; i < args.size(); i = i + 2) {
                nameValuePair.add(new BasicNameValuePair(args.get(i).toString(), args.get(i + 1).toString()));
            }
            String paramString = URLEncodedUtils.format(nameValuePair, "utf-8");
            url += paramString;
            Log.e("test", url);
            HttpGet httpGet = new HttpGet(url);

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            return responseString;
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String str) {
        if (this.exception != null)
            Log.e("test", this.exception.getMessage());
        try {
            methodMap.get(route).invoke(RequestController.getInstance(), context, str);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

