package root.epiandroid.observer;

/**
 * Created by vesy_m on 15/01/15.
 */
public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(Object... objs);
}