package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import root.epiandroid.MainActivity;
import root.epiandroid.R;

/**
 * Created by vesy_m on 15/01/15.
 */
public class SusieFragment extends AbstractObserverFragment {

    public SusieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.susie, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(6);
    }

    @Override
    public void update(Object... objs) {
    }
}
