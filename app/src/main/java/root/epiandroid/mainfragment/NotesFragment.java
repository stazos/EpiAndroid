package root.epiandroid.mainfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
        Activity act = getActivity();
        displayLoading();
        Button button = (Button) act.findViewById(R.id.note_reload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                NoteController.getInstance().NoteReload();
            }
        });
        NoteController.getInstance().addObserver(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(3);
    }

    public void displayLoading() {
        Activity act = getActivity();
        ListView listview = (ListView) act.findViewById(R.id.notes_list);
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.notes_progress);
        Button button = (Button) act.findViewById(R.id.note_reload);

        listview.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        bar.setVisibility(View.VISIBLE);
    }

    public void displayContent() {
        Activity act = getActivity();
        ListView listview = (ListView) act.findViewById(R.id.notes_list);
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.notes_progress);
        Button button = (Button) act.findViewById(R.id.note_reload);

        button.setVisibility(View.INVISIBLE);
        bar.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.VISIBLE);
    }

    public void displayError() {
        Activity act = getActivity();
        ListView listview = (ListView) act.findViewById(R.id.notes_list);
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.notes_progress);
        Button button = (Button) act.findViewById(R.id.note_reload);

        bar.setVisibility(View.INVISIBLE);
        listview.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
    }

    @Override
    public void update(Object... objs) {
        Object[] listArgs = objs.clone();
        String error = (String) listArgs[0];
        String token = (String) listArgs[1];
        String login = (String) listArgs[2];
        List<Note> listNotes = (List<Note>) listArgs[3];
        Activity act = getActivity();
        if (error != null) {
            RequestController.getInstance().stopAllRequest();
            displayError();

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(act, error, duration);
            toast.show();
        } else {
            if (listNotes == null) {
                RequestController.getInstance().get(act, "/marks", "token", token);
            }
            if (listNotes != null) {
                ListView listview = (ListView) act.findViewById(R.id.notes_list);

                NoteListAdapter adapter = new NoteListAdapter(act, R.layout.note_list_row, listNotes);
                listview.setAdapter(adapter);

                displayContent();
            }
        }
    }
}
