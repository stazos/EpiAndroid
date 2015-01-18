package root.epiandroid.controller;

import android.graphics.Bitmap;

import java.util.List;

import root.epiandroid.model.Message;
import root.epiandroid.model.ProfilModel;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 14/01/15.
 */
public class ProfilController {

    private static final ProfilController INSTANCE = new ProfilController();

    public ProfilController() {

    }

    public static ProfilController getInstance() {
        return INSTANCE;
    }

    public void addObserver(Observer obs) {
        session.addObserver(obs);
    }

    private ProfilModel session = new ProfilModel();


    public void profilReload() {
        session.profilReload();
    }

    public void setTokenAndLogin(String token, String login) {
        session.setToken(token);
        session.setLogin(login);
    }

    public void setError(String error) {
        session.setError("Impossible d'obtenir la photo de profil");
    }

    public void setPathPicture(String pathPicture) {
        if (pathPicture == null)
            session.setError("Impossible d'obtenir la photo de profil");
        else
            session.setPathPicture(pathPicture);
    }

    public void setPicture(Bitmap image) {
        session.setPicture(image);
    }


    public void setLogTime(String logTime) {
        if (logTime == null)
            session.setError("Impossible d'obtenir Log time");
        else
            session.setLogTime(logTime);
    }

    public void setListMessages(List<Message> listMessages) {
        session.initListMessage();
        for (Message msg : listMessages) {
            session.addMessage(msg);
        }
        session.notifyObserver();
    }

}
