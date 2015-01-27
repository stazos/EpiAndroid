package root.epiandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import root.epiandroid.R;
import root.epiandroid.model.object.Module;

/**
 * Created by vesy_m on 19/01/15.
 */
public class ModuleListAdapter extends ArrayAdapter<Module> {

    public ModuleListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ModuleListAdapter(Context context, int resource, List<Module> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.module_list_row, null);

        }

        final Module module = getItem(position);

        if (module != null) {

            TextView title = (TextView) v.findViewById(R.id.module_row_title);
            title.setText(module.getTitle());

            TextView grade = (TextView) v.findViewById(R.id.module_row_date_start);
            grade.setText(module.getGrade());

            TextView semester = (TextView) v.findViewById(R.id.module_row_title_module);
            semester.setText("semestre " + module.getSemester());

            TextView credit = (TextView) v.findViewById(R.id.module_row_date_end);
            credit.setText(module.getCredits());

            if (module.getGrade().equals("Echec"))
                v.setBackgroundColor(getContext().getResources().getColor(R.color.red));
            else if (module.getGrade().equals("-"))
                v.setBackgroundColor(getContext().getResources().getColor(R.color.background_floating_material_light));
            else if (!module.getGrade().equals("Echec"))
                v.setBackgroundColor(getContext().getResources().getColor(R.color.green));
            else
                v.setBackgroundColor(getContext().getResources().getColor(R.color.background_floating_material_light));
        }
        return v;

    }
}
