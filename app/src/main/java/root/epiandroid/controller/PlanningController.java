package root.epiandroid.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import root.epiandroid.model.object.Event;
import root.epiandroid.model.PlanningModel;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 14/01/15.
 */
public class PlanningController {

    private static final PlanningController INSTANCE = new PlanningController();

    public PlanningController() {

    }

    public static PlanningController getInstance() {
        return INSTANCE;
    }


    private String _login;

    private String _token;

    private PlanningModel planningModel = new PlanningModel();

    public void addObserver(Observer obs) {
        planningModel.addObserver(obs);
    }

    public void setError(String error) {
        planningModel.setError(error);
    }

    public void clearListEvent() {
        planningModel.delListEvent();
    }

    public Date getCurrentMonday() {
        return planningModel.getCurrentMonday();
    }

    public void setCurrentMonday(Date currentMonday) {
        planningModel.setCurrentMonday(currentMonday);
    }

    public Date getPrevMonday() {
        Date date = planningModel.getCurrentMonday();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        GregorianCalendar newDate = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        newDate.add(Calendar.DATE, -1);
        while (newDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
            newDate.add(Calendar.DATE, -1);
        return newDate.getTime();
    }

    public Date getNextMonday() {
        Date date = planningModel.getCurrentMonday();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        GregorianCalendar newDate = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        newDate.add(Calendar.DATE, 1);
        while (newDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
            newDate.add(Calendar.DATE, 1);
        return newDate.getTime();
    }

    public String getWeekString() {
        SimpleDateFormat sdf = new SimpleDateFormat("E dd-MM-yyyy");
        String dateStart = sdf.format(getCurrentMonday());
        Calendar c = Calendar.getInstance();
        c.setTime(PlanningController.getInstance().getCurrentMonday());
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String dateEnd = sdf.format(c.getTime());
        return dateStart + "\n" + dateEnd;
    }

    public void setTokenAndLogin(String token, String login) {
        planningModel.setToken(token);
        planningModel.setLogin(login);
    }

    public void planningReload() {
        planningModel.planningReload();
    }

    public void setListEvents(List<Event> listEvents) {
        planningModel.setListEvents(listEvents);
    }

    public void setFilter(int filter) {
        planningModel.setFilter(filter);
    }
}
