package root.epiandroid.model;

import java.lang.reflect.Method;
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
    private int filter = 0;
    private List<Module> listModule = null;

    public void reload() {
        error = null;
        listModule = null;
        notifyObserver();
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
        reload();
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

    public Boolean filter0(Module module) {
        //tous
        return true;
    }

    public Boolean filter1(Module module) {
        //mon semestre
        if (module.getSemester().equals("5"))
            return true;
        return false;
    }

    public Boolean filter2(Module module) {
        //en cour
        if (module.getGrade().equals("-"))
            return true;
        return false;
    }

    public Boolean filter3(Module module) {
        //acquis
        if (!module.getGrade().equals("Echec") && !module.getGrade().equals("-"))
            return true;
        return false;
    }

    public Boolean filter4(Module module) {
        //echec
        if (module.getGrade().equals("Echec"))
            return true;
        return false;
    }

    public void setListModules(List<Module> listModules) {
        if (listModule == null)
            listModule = new ArrayList<Module>();
        Method mth = null;
        try {
            mth = ModuleModel.class.getDeclaredMethod("filter" + filter, Module.class);
            for (Module Module : listModules) {
                if (!Module.getTitle().equals("") && (Boolean) mth.invoke(this, Module))
                    listModule.add(Module);
            }
        } catch (Exception e) {
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
