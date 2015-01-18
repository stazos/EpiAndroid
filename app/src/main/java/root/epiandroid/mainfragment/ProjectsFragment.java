package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import root.epiandroid.MainActivity;
import root.epiandroid.R;

/**
 * Created by vesy_m on 15/01/15.
 */
public class ProjectsFragment extends AbstractObserverFragment {

    public ProjectsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.projects, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(5);
    }

    @Override
    public void update(Object... objs) {
        Log.e("test", "updateProject");
    }
}
