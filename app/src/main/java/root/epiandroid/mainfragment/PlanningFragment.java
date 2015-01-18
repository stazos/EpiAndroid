package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PlanningController.getInstance().addObserver(this);
        Activity act = getActivity();
        Button prevButton = (Button) act.findViewById(R.id.planning_button_prev);
        Button nextButton = (Button) act.findViewById(R.id.planning_button_next);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity act = getActivity();
                Date prevMonday = PlanningController.getInstance().getPrevMonday();
                ProgressBar bar = (ProgressBar) act.findViewById(R.id.planing_progress);
                ListView listview = (ListView) act.findViewById(R.id.planning_list);
                TextView planningText = (TextView) act.findViewById(R.id.planning_text);
                PlanningController.getInstance().setCurrentMonday(prevMonday);
                listview.setVisibility(View.INVISIBLE);
                planningText.setVisibility(View.INVISIBLE);
                bar.setVisibility(View.VISIBLE);
                PlanningController.getInstance().clearListEvent();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity act = getActivity();
                Date newMonday = PlanningController.getInstance().getNextMonday();
                ProgressBar bar = (ProgressBar) act.findViewById(R.id.planing_progress);
                ListView listview = (ListView) act.findViewById(R.id.planning_list);
                TextView planningText = (TextView) act.findViewById(R.id.planning_text);
                PlanningController.getInstance().setCurrentMonday(newMonday);
                listview.setVisibility(View.INVISIBLE);
                planningText.setVisibility(View.INVISIBLE);
                bar.setVisibility(View.VISIBLE);
                PlanningController.getInstance().clearListEvent();
            }
        });
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStart = sdf.format(currentMonday);
        Log.e("test", dateStart);

        Calendar c = Calendar.getInstance();
        c.setTime(currentMonday);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String dateEnd = sdf.format(c.getTime());
        Log.e("test", dateEnd);

        if (listEvents == null) {
            RequestController.getInstance().get(act, "/planning", "token", token, "start", dateStart, "end", dateEnd);
        }
        if (listEvents != null) {
            ListView listview = (ListView) act.findViewById(R.id.planning_list);
            ArrayList<String> list = new ArrayList<String>();
            for (Event msg : listEvents) {
                list.add(msg.getActiTitle());
            }
            ListAdapter adapter = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);

            TextView planningText = (TextView) act.findViewById(R.id.planning_text);
            planningText.setText(PlanningController.getInstance().getWeekString());

            ProgressBar bar = (ProgressBar) act.findViewById(R.id.planing_progress);
            bar.setVisibility(View.INVISIBLE);
            listview.setVisibility(View.VISIBLE);
            planningText.setVisibility(View.VISIBLE);

        }

    }

}
