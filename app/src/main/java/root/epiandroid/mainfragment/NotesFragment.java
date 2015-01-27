package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.adapter.NoteListAdapter;
import root.epiandroid.controller.NoteController;
import root.epiandroid.controller.RequestController;
import root.epiandroid.model.object.Note;

/**
 * Created by vesy_m on 15/01/15.
 */
public class NotesFragment extends AbstractObserverFragment {

    public NotesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notes, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NoteController.getInstance().addObserver(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(3);
    }

    @Override
    public void update(Object... objs) {
        Object[] listArgs = objs.clone();
        String error = (String) listArgs[0];
        String token = (String) listArgs[1];
        String login = (String) listArgs[2];
        List<Note> listNotes = (List<Note>) listArgs[3];
        Activity act = getActivity();

        if (listNotes == null) {
            RequestController.getInstance().get(act, "/marks", "token", token);
        }
        if (listNotes != null) {
            ListView listview = (ListView) act.findViewById(R.id.notes_list);

            NoteListAdapter adapter = new NoteListAdapter(act, R.layout.note_list_row, listNotes);
            listview.setAdapter(adapter);

            ProgressBar bar = (ProgressBar) act.findViewById(R.id.notes_progress);
            bar.setVisibility(View.INVISIBLE);

            listview.setVisibility(View.VISIBLE);
        }

    }
}
