package root.epiandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import root.epiandroid.EventActivity;
import root.epiandroid.R;
import root.epiandroid.model.object.Event;

/**
 * Created by vesy_m on 19/01/15.
 */
public class PlanningListAdapter extends ArrayAdapter<Event> {

    private List<Integer> listPosWithNoDate = null;

    public PlanningListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PlanningListAdapter(Context context, int resource, List<Event> items) {
        super(context, resource, items);
        listPosWithNoDate = new ArrayList<>();
        SimpleDateFormat formatDate = new SimpleDateFormat("EEEE d MMM");
        String prevDate = null;
        for (int i = 0; i < items.size(); i++) {
            String currentDate = formatDate.format(items.get(i).getStart());
            if (prevDate != null && currentDate.equals(prevDate)) {
                listPosWithNoDate.add(i);
            }
            prevDate = currentDate;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //System.out.println("getView " + position + " " + convertView);
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


            String currentDate = formatDate.format(event.getStart());
            TextView date = (TextView) v.findViewById(R.id.planning_row_date);
            date.setText(currentDate);

            TextView title = (TextView) v.findViewById(R.id.planning_row_title);
            title.setText(event.getActiTitle() + "\n" + event.getTitleModule());

            TextView salle = (TextView) v.findViewById(R.id.planning_row_salle);
            salle.setText(event.getSalle());

            TextView hour = (TextView) v.findViewById(R.id.planning_row_hour);
            hour.setText(formatHour.format(event.getStart()));

            // System.out.println(currentDate + "   -   " + prevDate);
            date.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            salle.setVisibility(View.VISIBLE);
            hour.setVisibility(View.VISIBLE);
//            String intString = "";
//            for (Integer in : listPosWithNoDate) {
//                intString += in + " ";
//            }
            // Log.e("test", intString);
            if (listPosWithNoDate.contains(position)) {
                date.setVisibility(View.GONE);
            }

            if (event.getRegistered().equals("null"))
                v.setBackgroundColor(getContext().getResources().getColor(R.color.background_material_light));
            else if (event.getRegistered().equals("present"))
                v.setBackgroundColor(getContext().getResources().getColor(R.color.green));
            else if (event.getAllowToken().equals("true"))
                v.setBackgroundColor(getContext().getResources().getColor(R.color.orange));
            else if (event.getRegistered().equals("registered"))
                v.setBackgroundColor(getContext().getResources().getColor(R.color.blue));

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
