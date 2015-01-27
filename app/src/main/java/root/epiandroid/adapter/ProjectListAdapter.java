package root.epiandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import root.epiandroid.R;
import root.epiandroid.model.object.Project;

/**
 * Created by vesy_m on 19/01/15.
 */
public class ProjectListAdapter extends ArrayAdapter<Project> {

    public ProjectListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ProjectListAdapter(Context context, int resource, List<Project> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.project_list_row, null);

        }

        final Project project = getItem(position);

        if (project != null) {

            TextView title = (TextView) v.findViewById(R.id.project_row_title);
            title.setText(project.getActiTitle());

            TextView titleModule = (TextView) v.findViewById(R.id.project_row_title_module);
            titleModule.setText(project.getTitleModule());

            TextView dateStart = (TextView) v.findViewById(R.id.project_row_date_start);
            dateStart.setText(project.getStart());

            TextView dateEnd = (TextView) v.findViewById(R.id.project_row_date_end);
            dateEnd.setText(project.getEnd());

            if (project.getRegistered().equals("1"))
                v.setBackgroundColor(getContext().getResources().getColor(R.color.green));
            else
                v.setBackgroundColor(getContext().getResources().getColor(R.color.background_floating_material_light));
        }
        return v;

    }
}
