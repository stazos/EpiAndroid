package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.model.SessionModel;

/**
 * Created by vesy_m on 15/01/15.
 */
public class TokenFragment extends AbstractObserverFragment {

    public TokenFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.token, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(3);
    }

    @Override
    public void update(SessionModel model) {
    }
}
