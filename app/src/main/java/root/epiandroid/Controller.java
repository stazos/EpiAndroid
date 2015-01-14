package root.epiandroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by vesy_m on 14/01/15.
 */
public class Controller {

    public final static String EXTRA_MESSAGE = "root.epiandroid.LoginActivity";

    public static void login(Context ctx, String str) {
        ObjectMapper mapper = new ObjectMapper();
        String token = null;
        try {
            JsonNode rootNode = mapper.readTree(str.getBytes());
            token = rootNode.get("token").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(ctx, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, token);
        ctx.startActivity(intent);

    }

    public static void infos(Context ctx, String str) {
        Log.e("test", "plopplop");
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(str.getBytes());
            JsonNode history = rootNode.get("infos");
            //JsonNode histOne = history.get("picture");
            //JsonNode user = histOne.get("picture");
            //Log.e("test", "https://cdn.local.epitech.eu/userprofil/" + user.toString());
            String pathPicture = history.get("picture").toString();
            pathPicture = pathPicture.substring(1, pathPicture.length() - 1);
            Log.e("test", "https://cdn.local.epitech.eu/userprofil/" + pathPicture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("test", "plop2");
    }
}
