package root.epiandroid.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import root.epiandroid.LoginActivity;
import root.epiandroid.MainActivity;
import root.epiandroid.model.Event;
import root.epiandroid.model.Message;
import root.epiandroid.request.GetRequest;
import root.epiandroid.request.ImageRequest;
import root.epiandroid.request.PostRequest;

/**
 * Created by vesy_m on 14/01/15.
 */
public class RequestController {

    private static final RequestController INSTANCE = new RequestController();

    public RequestController() {

    }

    public static RequestController getInstance() {
        return INSTANCE;
    }

    public final static String EXTRA_MESSAGE = "root.epiandroid.LoginActivity";

    private String _login;

    private String _token;

    public void setToken(String newToken) {
        _token = newToken;
    }

    public void setLogin(String newLogin) {
        _login = newLogin;
    }

    public JsonNode getNodeTree(String str) {
        if (str == null)
            return null;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(str.getBytes());
        } catch (Exception e) {
            Log.e("test", "fail get Tree");
        }
        return rootNode;
    }

    public String nodeToString(JsonNode node, String... listStr) {
        if (node == null)
            return null;
        JsonNode tmp = node;
        for (String str : listStr) {
            if (str == null)
                return null;
            tmp = tmp.get(str);
            if (tmp == null)
                return null;
        }
        String returnString = tmp.toString();
        if (returnString.startsWith("\"") && returnString.endsWith("\"") && returnString.length() > 1)
            returnString = returnString.substring(1, returnString.length() - 1);
        return returnString;
    }

    public ImageRequest image(String url) {
        ImageRequest req = new ImageRequest(url);
        req.execute();
        return req;
    }

    public PostRequest post(Context ctx, Object... objs) {
        PostRequest post = new PostRequest(ctx);
        post.execute(objs);
        return post;
    }

    public GetRequest get(Context ctx, Object... objs) {
        GetRequest get = new GetRequest(ctx);
        get.execute(objs);
        return get;
    }

    public void login(Context ctx, String str) {
        Log.e("test", str);
        JsonNode rootNode = getNodeTree(str);
        String token = nodeToString(rootNode, "token");
        if (token == null) {
            ((LoginActivity) ctx).onError();
            return;
        }
        setToken(token);
        ProfilController.getInstance().setTokenAndLogin(_token, _login);
        PlanningController.getInstance().setTokenAndLogin(_token, _login);
        Intent intent = new Intent(ctx, MainActivity.class);
        ctx.startActivity(intent);
    }

    public void getPhoto(Context ctx, String str) {
        JsonNode rootNode = getNodeTree(str);
        String pathPicture = nodeToString(rootNode, "url");
        ProfilController.getInstance().setPathPicture(pathPicture);
    }

    public void setPicture(Bitmap image) {
        ProfilController.getInstance().setPicture(image);
    }

    public void getLog(Context ctx, String str) {
        JsonNode rootNode = getNodeTree(str);
        String log = nodeToString(rootNode, "current", "active_log");
        ProfilController.getInstance().setLogTime(log);
    }

    public void getMessages(Context ctx, String str) {
        JsonNode rootNode = getNodeTree(str);
        if (rootNode == null) {
            ProfilController.getInstance().setError("Impossible d'obtenir les messages");
            return;
        }
        int i = 0;
        List<Message> listMessages = new ArrayList<>();
        JsonNode nodeMessage = null;
        while ((nodeMessage = rootNode.get(i)) != null) {
            Log.e("test", nodeMessage.toString());
            Message msg = new Message();
            msg.setTitle(nodeToString(nodeMessage, "title"));
            msg.setContent(nodeToString(nodeMessage, "content"));
            msg.setDate(nodeToString(nodeMessage, "date"));
            msg.setUserLabel(nodeToString(nodeMessage, "user", "title"));
            msg.setUserPicture(nodeToString(nodeMessage, "user", "picture"));
            listMessages.add(msg);
            i = i + 1;
        }
        ProfilController.getInstance().setListMessages(listMessages);
    }

    public void getPlanning(Context ctx, String str) {
        JsonNode rootNode = getNodeTree(str);
        if (rootNode == null) {
            PlanningController.getInstance().setError("Impossible d'obtenir les evenements");
            return;
        }
        int i = 0;
        List<Event> listEvents = new ArrayList<>();
        JsonNode nodeEvent = null;
        while ((nodeEvent = rootNode.get(i)) != null) {
            Log.e("test", nodeEvent.toString());
            if (!nodeToString(nodeEvent, "event_registered").equals("null")) {
                Event event = new Event();
                event.setActiTitle(nodeToString(nodeEvent, "acti_title"));
                event.setCodeActi(nodeToString(nodeEvent, "codeacti"));
                event.setCodeEvent(nodeToString(nodeEvent, "codeevent"));
                event.setCodeInstance(nodeToString(nodeEvent, "codeinstance"));
                event.setCodeModule(nodeToString(nodeEvent, "codemodule"));
                event.setEnd(nodeToString(nodeEvent, "end"));
                event.setSalle(nodeToString(nodeEvent, "room", "code"));
                event.setScolarYear(nodeToString(nodeEvent, "scolaryear"));
                event.setStart(nodeToString(nodeEvent, "start"));
                listEvents.add(event);
            }
            i = i + 1;
        }
        PlanningController.getInstance().setListEvents(listEvents);
    }
}
