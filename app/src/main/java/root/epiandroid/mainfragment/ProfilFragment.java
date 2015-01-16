package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.model.SessionModel;

/**
 * Created by vesy_m on 15/01/15.
 */
public class ProfilFragment extends AbstractObserverFragment {

    View rootView = null;

    public ProfilFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profil, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(1);
    }

    @Override
    public void update(SessionModel model) {
        Log.e("test", "updateProfil");
        Activity act = getActivity();
        TextView text = (TextView) act.findViewById(R.id.profiltext);
        text.setText(model.getLogin());
    }
}
