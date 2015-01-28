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
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.adapter.ProjectListAdapter;
import root.epiandroid.controller.ProjectController;
import root.epiandroid.controller.RequestController;
import root.epiandroid.model.object.Project;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity act = getActivity();
        displayLoading();
        Button button = (Button) act.findViewById(R.id.project_reload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ProjectController.getInstance().ProjectReload();
            }
        });
        ProjectController.getInstance().addObserver(this);
    }

    public void displayLoading() {
        Activity act = getActivity();
        ListView listview = (ListView) act.findViewById(R.id.projects_list);
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.project_progress);
        Button button = (Button) act.findViewById(R.id.project_reload);

        button.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.INVISIBLE);
        bar.setVisibility(View.VISIBLE);
    }

    public void displayContent() {
        Activity act = getActivity();
        ListView listview = (ListView) act.findViewById(R.id.projects_list);
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.project_progress);
        Button button = (Button) act.findViewById(R.id.project_reload);

        button.setVisibility(View.INVISIBLE);
        bar.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.VISIBLE);
    }

    public void displayError() {
        Activity act = getActivity();
        ListView listview = (ListView) act.findViewById(R.id.projects_list);
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.project_progress);
        Button button = (Button) act.findViewById(R.id.project_reload);

        bar.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(5);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.reload_menu, menu);
        inflater.inflate(R.menu.project_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reload_option_menu:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ProjectController.getInstance().ProjectReload();
                return true;
            case R.id.project_option_tout:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ProjectController.getInstance().setFilter(0);
                return true;
            case R.id.project_option_inscrit:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ProjectController.getInstance().setFilter(1);
                return true;
            case R.id.project_option_non_inscrit:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ProjectController.getInstance().setFilter(2);
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
        List<Project> listProjects = (List<Project>) listArgs[3];
        Activity act = getActivity();

        if (error != null) {
            RequestController.getInstance().stopAllRequest();
            displayError();

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(act, error, duration);
            toast.show();
        } else {
            if (listProjects == null) {
                RequestController.getInstance().get(act, "/projects", "token", token);
            }
            if (listProjects != null) {
                ListView listview = (ListView) act.findViewById(R.id.projects_list);

                ProjectListAdapter adapter = new ProjectListAdapter(act, R.layout.project_list_row, listProjects);
                listview.setAdapter(adapter);

                displayContent();
            }
        }

    }
}
