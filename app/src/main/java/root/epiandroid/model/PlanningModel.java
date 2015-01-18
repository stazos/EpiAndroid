package root.epiandroid.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import root.epiandroid.model.object.Event;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 15/01/15.
 */
public class PlanningModel extends AbstractModel {

    private String error = null;
    private String token = null;
    private String login = null;
    private Date currentMonday = null;
    private List<Event> listEvent = null;

    public void planningReload() {
        error = null;
        listEvent = null;
        currentMonday = null;
        notifyObserver();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        notifyObserver();
    }

    public PlanningModel() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        currentMonday = c.getTime();
    }

    public Date getCurrentMonday() {
        return currentMonday;
    }

    public void setCurrentMonday(Date currentMonday) {
        this.currentMonday = currentMonday;
        notifyObserver();
    }

    public void delListEvent() {
        listEvent = null;
        notifyObserver();
    }

    public List<Event> getListEvent() {
        return listEvent;
    }

    public void setListEvents(List<Event> listEvents) {
        if (listEvent == null)
            listEvent = new ArrayList<Event>();
        for (Event event : listEvents) {
            // if (event.get!nodeToString(nodeEvent, "event_registered").equals("null")) {
            listEvent.add(event);
        }
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
        super.notifyObserver(error, token, login, currentMonday, listEvent);
    }
}
