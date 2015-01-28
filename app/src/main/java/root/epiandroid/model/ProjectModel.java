package root.epiandroid.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import root.epiandroid.model.object.Project;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 15/01/15.
 */
public class ProjectModel extends AbstractModel {

    private String error = null;
    private String token = null;
    private String login = null;
    private int filter = 0;
    private List<Project> listProject = null;

    public void reload() {
        error = null;
        listProject = null;
        notifyObserver();
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
        reload();
    }

    public ProjectModel() {
    }

    public void delListProjects() {
        listProject = null;
        notifyObserver();
    }

    public List<Project> getListProjects() {
        return listProject;
    }

    public Boolean filter0(Project project) {
        //tous
        return true;
    }

    public Boolean filter1(Project project) {
        //inscrit
        if (project.getRegistered().equals("1"))
            return true;
        return false;
    }

    public Boolean filter2(Project project) {
        //non inscrit
        if (project.getRegistered().equals("0"))
            return true;
        return false;
    }

    public void setListProjects(List<Project> listProjects) {
        if (listProject == null)
            listProject = new ArrayList<Project>();
        Method mth = null;
        try {
            mth = ProjectModel.class.getDeclaredMethod("filter" + filter, Project.class);
            for (Project project : listProjects) {
                if ((Boolean) mth.invoke(this, project))
                    listProject.add(project);
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
        super.notifyObserver(error, token, login, listProject);
    }
}
