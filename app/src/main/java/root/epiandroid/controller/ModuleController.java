package root.epiandroid.controller;

import java.util.List;

import root.epiandroid.model.ModuleModel;
import root.epiandroid.model.object.Module;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 14/01/15.
 */
public class ModuleController {

    private static final ModuleController INSTANCE = new ModuleController();

    public ModuleController() {

    }

    public static ModuleController getInstance() {
        return INSTANCE;
    }

    private String _login;

    private String _token;

    private ModuleModel moduleModel = new ModuleModel();

    public void addObserver(Observer obs) {
        moduleModel.addObserver(obs);
    }

    public void setError(String error) {
        moduleModel.setError(error);
    }

    public void clearListModules() {
        moduleModel.delListModules();
    }


    public void setTokenAndLogin(String token, String login) {
        moduleModel.setToken(token);
        moduleModel.setLogin(login);
    }

    public void ModuleReload() {
        moduleModel.reload();
    }

    public void setListModules(List<Module> listModules) {
        moduleModel.setListModules(listModules);
    }

    public void setFilter(int filter) {
        moduleModel.setFilter(filter);
    }
}
