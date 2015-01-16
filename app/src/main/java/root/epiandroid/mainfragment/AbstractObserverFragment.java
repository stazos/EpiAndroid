package root.epiandroid.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import root.epiandroid.Controller;
import root.epiandroid.model.SessionModel;
import root.epiandroid.observer.Observer;

/**
 * Created by vesy_m on 16/01/15.
 */
public class AbstractObserverFragment extends Fragment implements Observer {
    @Override
    public void update(SessionModel model) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Controller.getInstance().addObserverToSessionModel(this);
    }
}
