package root.epiandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import root.epiandroid.R;
import root.epiandroid.model.object.Note;

/**
 * Created by vesy_m on 19/01/15.
 */
public class NoteListAdapter extends ArrayAdapter<Note> {

    public NoteListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public NoteListAdapter(Context context, int resource, List<Note> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.note_list_row, null);

        }

        final Note note = getItem(position);

        if (note != null) {

            TextView noteText = (TextView) v.findViewById(R.id.note_row_note);
            noteText.setText(note.getFinal_note());

            TextView title = (TextView) v.findViewById(R.id.note_row_title);
            title.setText(note.getTitle());

            TextView titleModule = (TextView) v.findViewById(R.id.note_row_title_module);
            titleModule.setText(note.getTitlemodule());

            TextView date = (TextView) v.findViewById(R.id.note_row_date);
            date.setText(note.getDate());

            TextView correcteur = (TextView) v.findViewById(R.id.note_row_correcteur);
            correcteur.setText(note.getCorrecteur());

            TextView comment = (TextView) v.findViewById(R.id.note_row_comment);
            comment.setText(note.getComment());
        }
        return v;

    }
}
