package root.epiandroid.observer;

import root.epiandroid.model.SessionModel;

/**
 * Created by vesy_m on 15/01/15.
 */
public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(SessionModel Model);
}