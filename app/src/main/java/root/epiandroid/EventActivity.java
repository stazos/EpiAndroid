package root.epiandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import root.epiandroid.controller.RequestController;
import root.epiandroid.model.object.Event;


public class EventActivity extends ActionBarActivity {

    private ProgressBar mProgress;

    public void displayContent(Boolean enterToken) {
        Log.e("test", enterToken.toString());
        ProgressBar bar = (ProgressBar) findViewById(R.id.event_progress);
        TextView eventTitle = (TextView) findViewById(R.id.event_title);
        Button tokenButton = (Button) findViewById(R.id.event_button);
        EditText tokenField = (EditText) findViewById(R.id.event_token);
        TextView titleModule = (TextView) findViewById(R.id.event_title_module);
        TextView duree = (TextView) findViewById(R.id.event_duree);
        TextView salle = (TextView) findViewById(R.id.event_salle);
        TextView statut = (TextView) findViewById(R.id.event_statut);
        bar.setVisibility(View.INVISIBLE);
        eventTitle.setVisibility(View.VISIBLE);
        titleModule.setVisibility(View.VISIBLE);
        duree.setVisibility(View.VISIBLE);
        salle.setVisibility(View.VISIBLE);
        statut.setVisibility(View.VISIBLE);
        if (enterToken) {
            tokenButton.setVisibility(View.VISIBLE);
            tokenField.setVisibility(View.VISIBLE);
        } else {
            tokenButton.setVisibility(View.INVISIBLE);
            tokenField.setVisibility(View.INVISIBLE);
        }
    }

    public void displayLoading() {
        ProgressBar bar = (ProgressBar) findViewById(R.id.event_progress);
        TextView eventTitle = (TextView) findViewById(R.id.event_title);
        Button tokenButton = (Button) findViewById(R.id.event_button);
        EditText tokenField = (EditText) findViewById(R.id.event_token);
        TextView titleModule = (TextView) findViewById(R.id.event_title_module);
        TextView duree = (TextView) findViewById(R.id.event_duree);
        TextView salle = (TextView) findViewById(R.id.event_salle);
        TextView statut = (TextView) findViewById(R.id.event_statut);
        bar.setVisibility(View.VISIBLE);
        eventTitle.setVisibility(View.INVISIBLE);
        tokenButton.setVisibility(View.INVISIBLE);
        tokenField.setVisibility(View.INVISIBLE);
        titleModule.setVisibility(View.INVISIBLE);
        duree.setVisibility(View.INVISIBLE);
        salle.setVisibility(View.INVISIBLE);
        statut.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);
        Intent intent = getIntent();
        final Event event = (Event) intent.getSerializableExtra("EVENT_MESSAGE");
        TextView title = (TextView) findViewById(R.id.event_title);
        title.setText(event.getActiTitle());
        TextView titleModule = (TextView) findViewById(R.id.event_title_module);
        titleModule.setText(event.getTitleModule());
        TextView duree = (TextView) findViewById(R.id.event_duree);
        duree.setText("durée : " + event.getDuree());
        TextView salle = (TextView) findViewById(R.id.event_salle);
        salle.setText("salle : " + event.getSalle());
        TextView statut = (TextView) findViewById(R.id.event_statut);
        statut.setText("statut : " + event.getRegistered());
        Button tokenButton = (Button) findViewById(R.id.event_button);
        tokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestController.getInstance().seteAct((EventActivity) v.getContext());
                EditText tokenField = (EditText) findViewById(R.id.event_token);
                displayLoading();
                RequestController.getInstance().post(v.getContext(), "/token",
                        "token", RequestController.getInstance().get_token(),
                        "scolaryear", event.getScolarYear(),
                        "codemodule", event.getCodeModule(),
                        "codeinstance", event.getCodeInstance(),
                        "codeacti", event.getCodeActi(),
                        "tokenvalidationcode", "\"" + tokenField.getText() + "\"",
                        "codeevent", event.getCodeEvent());
            }
        });
        displayContent(Boolean.valueOf(event.getAllowToken()));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onTokenResponse(String str) {
        Log.e("test", str);
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(this, str, duration);
        toast.show();
        displayContent(false);
    }
}
