package root.epiandroid.controller;

import android.graphics.Bitmap;

import java.util.List;

import root.epiandroid.model.object.Message;
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
        session.setError(error);
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
        session.setListMessages(listMessages);
    }

    public void setTitle(String title) {
        session.setTitle(title);
    }

    public void setCurrentCredit(String currentCredit) {
        session.setCurrentCredit(currentCredit);
    }

    public void setFailCredit(String failCredit) {
        session.setFailCredit(failCredit);
    }

    public void setInProgressCredit(String inProgressCredit) {
        session.setInProgressCredit(inProgressCredit);
    }

    public void setSemesterNum(String semesterNum) {
        session.setSemesterNum(semesterNum);
    }

    public void setPromo(String promo) {
        session.setPromo(promo);
    }


}
