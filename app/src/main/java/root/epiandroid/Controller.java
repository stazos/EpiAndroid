package root.epiandroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import root.epiandroid.model.SessionModel;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 14/01/15.
 */
public class Controller {

    private static final Controller INSTANCE = new Controller();

    public Controller() {

    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    public void addObserverToSessionModel(Observer obs) {
        session.addObserver(obs);
    }

    public final static String EXTRA_MESSAGE = "root.epiandroid.LoginActivity";

    private SessionModel session = new SessionModel();

    public void setToken(String token) {
        session.setToken(token);
    }

    public void setLogin(String login) {
        session.setLogin(login);
    }

    public void setPicture(String pathPicture) {
        session.setPhotoPath(pathPicture);
    }

    public void post(Context ctx, Object... objs) {
        PostRequest post = new PostRequest(ctx);
        if (session.getToken() != null)
            post.execute(objs, "token", session.getToken());
        else
            post.execute(objs);
    }

    public void get(Context ctx, Object... objs) {
        PostRequest post = new PostRequest(ctx);
        if (session.getToken() != null)
            post.execute(objs, "token", session.getToken());
        else
            post.execute(objs);
    }

    public void login(Context ctx, String str) {
        if (str == null)
            ((LoginActivity) ctx).onResume();
        ObjectMapper mapper = new ObjectMapper();
        String token = null;
        try {
            JsonNode rootNode = mapper.readTree(str.getBytes());
            token = rootNode.get("token").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setToken(token);
        Intent intent = new Intent(ctx, MainActivity.class);
        ctx.startActivity(intent);

    }

    public void infos(Context ctx, String str) {
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

    public void getPhoto(Context ctx, String str) {
        Log.e("test", str);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(str.getBytes());
            Log.e("test", rootNode.get("url").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
