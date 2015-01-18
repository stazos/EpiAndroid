package root.epiandroid.model;

import java.util.ArrayList;
import java.util.List;

import root.epiandroid.model.object.Module;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 15/01/15.
 */
public class ModuleModel extends AbstractModel {

    private String error = null;
    private String token = null;
    private String login = null;
    private List<Module> listModule = null;

    public void reload() {
        error = null;
        listModule = null;
        notifyObserver();
    }

    public ModuleModel() {
    }

    public void delListModules() {
        listModule = null;
        notifyObserver();
    }

    public List<Module> getListModules() {
        return listModule;
    }

    public void setListModules(List<Module> listModules) {
        if (listModule == null)
            listModule = new ArrayList<Module>();
        for (Module Module : listModules) {
            listModule.add(Module);
        }
        notifyObserver();
    }

    /* basic tout les model on les meme fonction */

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        notifyObserver();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        notifyObserver();
    }

    public void notifyObserver() {
        super.notifyObserver(error, token, login, listModule);
    }
}
