package root.epiandroid.model;

import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 15/01/15.
 */
public class SessionModel extends AbstractModel {

    private String token = null;
    private String login = null;
    private String photoPath = null;

    public SessionModel() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        notifyObserver(this);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyObserver(this);
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        notifyObserver(this);
    }

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        notifyObserver(this);
    }
}
