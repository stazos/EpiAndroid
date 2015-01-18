package root.epiandroid.controller;

import java.util.List;

import root.epiandroid.model.ProjectModel;
import root.epiandroid.model.object.Project;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 14/01/15.
 */
public class SusieController {

    private static final SusieController INSTANCE = new SusieController();

    public SusieController() {

    }

    public static SusieController getInstance() {
        return INSTANCE;
    }

    private String _login;

    private String _token;

    private ProjectModel projectModel = new ProjectModel();

    public void addObserver(Observer obs) {
        projectModel.addObserver(obs);
    }

    public void setError(String error) {
        projectModel.setError("Impossible d'obtenir la photo de profil");
    }

    public void clearListEvent() {
        projectModel.delListProjects();
    }


    public void setTokenAndLogin(String token, String login) {
        projectModel.setToken(token);
        projectModel.setLogin(login);
    }

    public void ProjectReload() {
        projectModel.reload();
    }

    public void setListProjects(List<Project> listProjects) {
        projectModel.setListProjects(listProjects);
    }
}
