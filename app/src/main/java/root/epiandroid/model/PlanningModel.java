package root.epiandroid.model;

import java.lang.reflect.Method;
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
    private int filter = 0;

    public void planningReload() {
        error = null;
        listEvent = null;
        currentMonday = null;
        notifyObserver();
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
        planningReload();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        notifyObserver();
    }

    public PlanningModel() {

    }

    public Date getCurrentMonday() {
        if (currentMonday == null) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            currentMonday = c.getTime();
        }
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

    public Boolean filter0(Event event) {
        //registered
        if (!event.getRegistered().equals("null"))
            return true;
        return false;
    }

    public Boolean filter1(Event event) {
        //tout
        return true;
    }

    public void setListEvents(List<Event> listEvents) {
        if (listEvent == null)
            listEvent = new ArrayList<Event>();
        Method mth = null;
        try {
            mth = PlanningModel.class.getDeclaredMethod("filter" + filter, Event.class);
            for (Event event : listEvents) {
                if ((Boolean) mth.invoke(this, event))
                    listEvent.add(event);
            }
        } catch (Exception e) {
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
        super.notifyObserver(error, token, login, getCurrentMonday(), listEvent);
    }
}
