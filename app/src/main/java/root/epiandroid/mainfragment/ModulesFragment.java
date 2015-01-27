package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.adapter.ModuleListAdapter;
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

    public void displayLoading() {
        Activity act = getActivity();
        ListView listview = (ListView) act.findViewById(R.id.modules_list);
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.module_progress);

        listview.setVisibility(View.INVISIBLE);
        bar.setVisibility(View.VISIBLE);
    }

    public void displayContent() {
        Activity act = getActivity();
        ListView listview = (ListView) act.findViewById(R.id.modules_list);
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.module_progress);

        bar.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.reload_menu, menu);
        inflater.inflate(R.menu.module_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reload_option_menu:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ModuleController.getInstance().ModuleReload();
                return true;
            case R.id.module_option_tout:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ModuleController.getInstance().setFilter(0);
                return true;
            case R.id.module_option_mon_semestre:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ModuleController.getInstance().setFilter(1);
                return true;
            case R.id.module_option_en_cour:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ModuleController.getInstance().setFilter(2);
                return true;
            case R.id.module_option_acquis:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ModuleController.getInstance().setFilter(3);
                return true;
            case R.id.module_option_echec:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ModuleController.getInstance().setFilter(4);
                return true;

        }
        return super.onOptionsItemSelected(item);
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

            ModuleListAdapter adapter = new ModuleListAdapter(act, R.layout.module_list_row, listModules);
            listview.setAdapter(adapter);

            displayContent();
        }
    }
}
