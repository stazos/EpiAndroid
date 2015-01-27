package root.epiandroid.model;

import java.util.ArrayList;
import java.util.List;

import root.epiandroid.model.object.Note;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 15/01/15.
 */
public class NoteModel extends AbstractModel {

    private String error = null;
    private String token = null;
    private String login = null;
    private List<Note> listNote = null;

    public void reload() {
        error = null;
        listNote = null;
        notifyObserver();
    }

    public NoteModel() {
    }

    public void delListNotes() {
        listNote = null;
        notifyObserver();
    }

    public List<Note> getListNotes() {
        return listNote;
    }

    public void setListNotes(List<Note> listNotes) {
        if (listNote == null)
            listNote = new ArrayList<Note>();
        for (Note Note : listNotes) {
            listNote.add(Note);
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
        super.notifyObserver(error, token, login, listNote);
    }
}
