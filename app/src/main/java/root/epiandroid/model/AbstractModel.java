package root.epiandroid.model;

import java.util.ArrayList;

import root.epiandroid.observer.Observable;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 15/01/15.
 */
public abstract class AbstractModel implements Observable {

    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public void notifyObserver(SessionModel model) {
        for (Observer obs : listObserver)
            obs.update(model);
    }

    public void removeObserver() {
        listObserver = new ArrayList<Observer>();
    }
}
