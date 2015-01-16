package root.epiandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class LoginActivity extends ActionBarActivity {

    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("test", "------------------------------------------------");
        setContentView(R.layout.activity_login);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setIndeterminate(true);
        mProgress.setVisibility(View.GONE);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.setVisibility(View.VISIBLE);
                EditText loginText = (EditText) findViewById(R.id.login);
                EditText passwordText = (EditText) findViewById(R.id.password);
                Button button = (Button) findViewById(R.id.button);
                button.setVisibility(View.GONE);
                loginText.setVisibility(View.GONE);
                passwordText.setVisibility(View.GONE);
                String login = loginText.getText().toString();
                String pass = passwordText.getText().toString();
                Controller.getInstance().setLogin(login);
                Controller.getInstance().post(LoginActivity.this, "/login",
                        "login", login,
                        "password", pass);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        EditText loginText = (EditText) findViewById(R.id.login);
        EditText passwordText = (EditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.button);
        bar.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        loginText.setVisibility(View.VISIBLE);
        passwordText.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
