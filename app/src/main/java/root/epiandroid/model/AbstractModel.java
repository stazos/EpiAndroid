package root.epiandroid.model;

import android.util.Log;

import root.epiandroid.observer.Observable;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 15/01/15.
 */
public abstract class AbstractModel implements Observable {

    private Observer observer = null;

    public void addObserver(Observer obs) {
        observer = obs;
        //this.listObserver.add(obs);
    }

    public void notifyObserver(Object... objs) {
        Log.e("test", "Observer Notification");
        if (observer != null)
            observer.update(objs);
        //        for (Observer obs : listObserver) {
//            Log.e("test", "Observer Notification");
//            obs.update(model);
//        }
    }

    public void removeObserver() {
        observer = null;
        //listObserver = new ArrayList<Observer>();
    }
}
