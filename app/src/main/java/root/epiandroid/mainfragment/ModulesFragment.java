package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.controller.ModuleController;
import root.epiandroid.controller.RequestController;
import root.epiandroid.model.object.Module;

/**
 * Created by vesy_m on 15/01/15.
 */
public class ModulesFragment extends AbstractObserverFragment {

    public ModulesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modules, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ModuleController.getInstance().addObserver(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(4);
    }

    @Override
    public void update(Object... objs) {
        Object[] listArgs = objs.clone();
        String error = (String) listArgs[0];
        String token = (String) listArgs[1];
        String login = (String) listArgs[2];
        List<Module> listModules = (List<Module>) listArgs[3];
        Activity act = getActivity();

        if (listModules == null) {
            RequestController.getInstance().get(act, "/modules", "token", token);
        }
        if (listModules != null) {
            ListView listview = (ListView) act.findViewById(R.id.modules_list);
            ArrayList<String> list = new ArrayList<String>();
            for (Module Module : listModules) {
                list.add(Module.getTitle());
            }
            ListAdapter adapter = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);

            ProgressBar bar = (ProgressBar) act.findViewById(R.id.module_progress);
            bar.setVisibility(View.INVISIBLE);

            listview.setVisibility(View.VISIBLE);
        }
    }
}
