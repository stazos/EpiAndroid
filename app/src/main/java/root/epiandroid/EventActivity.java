package root.epiandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import root.epiandroid.model.object.Event;


public class EventActivity extends ActionBarActivity {

    private ProgressBar mProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);
        Intent intent = getIntent();
        Event event = (Event) intent.getSerializableExtra("EVENT_MESSAGE");
        TextView title = (TextView) findViewById(R.id.event_title); // title
        title.setText(event.getActiTitle());
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
