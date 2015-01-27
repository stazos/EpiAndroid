package root.epiandroid.controller;

import java.util.List;

import root.epiandroid.model.NoteModel;
import root.epiandroid.model.object.Note;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 14/01/15.
 */
public class NoteController {

    private static final NoteController INSTANCE = new NoteController();

    public NoteController() {

    }

    public static NoteController getInstance() {
        return INSTANCE;
    }

    private String _login;

    private String _token;

    private NoteModel noteModel = new NoteModel();

    public void addObserver(Observer obs) {
        noteModel.addObserver(obs);
    }

    public void setError(String error) {
        noteModel.setError(error);
    }

    public void clearListEvent() {
        noteModel.delListNotes();
    }


    public void setTokenAndLogin(String token, String login) {
        noteModel.setToken(token);
        noteModel.setLogin(login);
    }

    public void NoteReload() {
        noteModel.reload();
    }

    public void setListNotes(List<Note> listNotes) {
        noteModel.setListNotes(listNotes);
    }
}
