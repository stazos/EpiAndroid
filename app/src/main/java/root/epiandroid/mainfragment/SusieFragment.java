package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.controller.SusieController;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SusieController.getInstance().addObserver(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(6);
    }

    @Override
    public void update(Object... objs) {
//        Object[] listArgs = objs.clone();
//        String error = (String) listArgs[0];
//        String token = (String) listArgs[1];
//        String login = (String) listArgs[2];
//        List<Susie> listSusies = (List<Susie>) listArgs[3];
//        Activity act = getActivity();
//
//        if (listSusies == null) {
//            RequestController.getInstance().get(act, "/Susies", "token", token);
//        }
//        if (listSusies != null) {
//            ListView listview = (ListView) act.findViewById(R.id.susies_list);
//            ArrayList<String> list = new ArrayList<String>();
//            for (Susie Susie : listSusies) {
//                list.add(Susie.getTitle());
//            }
//            ListAdapter adapter = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, list);
//            listview.setAdapter(adapter);
//
//            ProgressBar bar = (ProgressBar) act.findViewById(R.id.susie_progress);
//            bar.setVisibility(View.INVISIBLE);
//
//            listview.setVisibility(View.VISIBLE);
//        }
    }
}
