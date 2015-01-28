package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.adapter.PlanningListAdapter;
import root.epiandroid.controller.PlanningController;
import root.epiandroid.controller.RequestController;
import root.epiandroid.model.object.Event;

/**
 * Created by vesy_m on 15/01/15.
 */
public class PlanningFragment extends AbstractObserverFragment {

    public PlanningFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.planning, container, false);
        return rootView;
    }

    public void displayContent() {
        Activity act = getActivity();
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.planning_progress);
        Button errorButton = (Button) act.findViewById(R.id.planning_reload);
        Button prevButton = (Button) act.findViewById(R.id.planning_button_prev);
        TextView planningText = (TextView) act.findViewById(R.id.planning_text);
        Button nextButton = (Button) act.findViewById(R.id.planning_button_next);
        ListView listview = (ListView) act.findViewById(R.id.planning_list);
        bar.setVisibility(View.INVISIBLE);
        errorButton.setVisibility(View.INVISIBLE);
        prevButton.setVisibility(View.VISIBLE);
        planningText.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        listview.setVisibility(View.VISIBLE);
    }

    public void displayLoading() {
        Activity act = getActivity();
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.planning_progress);
        Button errorButton = (Button) act.findViewById(R.id.planning_reload);
        Button prevButton = (Button) act.findViewById(R.id.planning_button_prev);
        TextView planningText = (TextView) act.findViewById(R.id.planning_text);
        Button nextButton = (Button) act.findViewById(R.id.planning_button_next);
        ListView listview = (ListView) act.findViewById(R.id.planning_list);
        bar.setVisibility(View.VISIBLE);
        errorButton.setVisibility(View.INVISIBLE);
        prevButton.setVisibility(View.INVISIBLE);
        planningText.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.INVISIBLE);
    }

    public void displayError() {
        Activity act = getActivity();
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.planning_progress);
        Button errorButton = (Button) act.findViewById(R.id.planning_reload);
        Button prevButton = (Button) act.findViewById(R.id.planning_button_prev);
        TextView planningText = (TextView) act.findViewById(R.id.planning_text);
        Button nextButton = (Button) act.findViewById(R.id.planning_button_next);
        ListView listview = (ListView) act.findViewById(R.id.planning_list);
        bar.setVisibility(View.INVISIBLE);
        errorButton.setVisibility(View.VISIBLE);
        prevButton.setVisibility(View.INVISIBLE);
        planningText.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        RequestController.getInstance().stopAllRequest();
        displayLoading();
        PlanningController.getInstance().planningReload();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.reload_menu, menu);
        inflater.inflate(R.menu.planning_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reload_option_menu:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                PlanningController.getInstance().planningReload();
                return true;
            case R.id.planning_option_enregistrer:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                PlanningController.getInstance().setFilter(0);
                return true;
            case R.id.planning_option_tout:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                PlanningController.getInstance().setFilter(1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        displayLoading();
        Activity act = getActivity();
        Button prevButton = (Button) act.findViewById(R.id.planning_button_prev);
        Button nextButton = (Button) act.findViewById(R.id.planning_button_next);
        Button reloadButton = (Button) act.findViewById(R.id.planning_reload);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestController.getInstance().stopAllRequest();
                Date prevMonday = PlanningController.getInstance().getPrevMonday();
                PlanningController.getInstance().setCurrentMonday(prevMonday);
                displayLoading();
                PlanningController.getInstance().clearListEvent();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestController.getInstance().stopAllRequest();
                Date newMonday = PlanningController.getInstance().getNextMonday();
                PlanningController.getInstance().setCurrentMonday(newMonday);
                displayLoading();
                PlanningController.getInstance().clearListEvent();
            }
        });
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                PlanningController.getInstance().planningReload();
            }
        });
        PlanningController.getInstance().addObserver(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(2);
    }

    @Override
    public void update(Object... objs) {
        Object[] listArgs = objs.clone();
        String error = (String) listArgs[0];
        String token = (String) listArgs[1];
        String login = (String) listArgs[2];
        Date currentMonday = (Date) listArgs[3];
        List<Event> listEvents = (List<Event>) listArgs[4];
        Log.e("test", "updatePlanning");
        Activity act = getActivity();

        if (error != null) {
            RequestController.getInstance().stopAllRequest();
            displayError();

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(act, error, duration);
            toast.show();
        } else {
            if (listEvents == null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStart = sdf.format(currentMonday);
                Log.e("test", dateStart);

                Calendar c = Calendar.getInstance();
                c.setFirstDayOfWeek(Calendar.MONDAY);
                c.setTime(currentMonday);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                String dateEnd = sdf.format(c.getTime());
                Log.e("test", dateEnd);
                RequestController.getInstance().get(act, "/planning", "token", token, "start", dateStart, "end", dateEnd);
            }
            if (listEvents != null) {
                System.out.println("iaiaiaiaiaiaaiaiaaiaiaiaiaiiaiaiaiaiaiaiaiaiaiaiaiai");
                Collections.sort(listEvents, new Comparator<Event>() {
                    public int compare(Event e1, Event e2) {
                        if (e1.getStart() == null || e2.getStart() == null)
                            return 0;
                        return e1.getStart().compareTo(e2.getStart());
                    }
                });

                ListView listview = (ListView) act.findViewById(R.id.planning_list);

                System.out.println("iaiaiaiaiaiaaiaiaaiaiaiaiaiiaiaiaiaiaiaiaiaiaiaiaiai");
                PlanningListAdapter adapter = new PlanningListAdapter(act, R.layout.planning_list_row, listEvents);
                listview.setAdapter(adapter);

                TextView planningText = (TextView) act.findViewById(R.id.planning_text);
                planningText.setText(PlanningController.getInstance().getWeekString());

                displayContent();
                System.out.println("ioioioioioioioioioioioioioioioiooioioioiooioioioioioio");

            }
        }

    }

}
