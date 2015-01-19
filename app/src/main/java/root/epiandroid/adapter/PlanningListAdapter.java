package root.epiandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import root.epiandroid.EventActivity;
import root.epiandroid.R;
import root.epiandroid.model.object.Event;

/**
 * Created by vesy_m on 19/01/15.
 */
public class PlanningListAdapter extends ArrayAdapter<Event> {

    public PlanningListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PlanningListAdapter(Context context, int resource, List<Event> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.planning_list_row, null);

        }

        final Event event = getItem(position);

        if (event != null) {
            SimpleDateFormat formatDate = new SimpleDateFormat("EEEE d MMM");
            SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm");

            TextView title = (TextView) v.findViewById(R.id.planning_row_title);
            title.setText(event.getActiTitle());

            TextView date = (TextView) v.findViewById(R.id.planning_row_date);
            date.setText(formatDate.format(event.getStart()));

            TextView salle = (TextView) v.findViewById(R.id.planning_row_salle);
            salle.setText(event.getSalle());

            TextView hour = (TextView) v.findViewById(R.id.planning_row_hour);
            hour.setText(formatHour.format(event.getStart()));
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                intent.putExtra("EVENT_MESSAGE", event);
                getContext().startActivity(intent);
            }
        });

        return v;

    }
}
