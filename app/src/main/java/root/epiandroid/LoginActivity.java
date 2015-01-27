package root.epiandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import root.epiandroid.controller.RequestController;


public class LoginActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("test", "------------------------------------------------");
        setContentView(R.layout.activity_login);
        displayContent();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loginText = (EditText) findViewById(R.id.login);
                EditText passwordText = (EditText) findViewById(R.id.password);
                String login = loginText.getText().toString();
                String pass = passwordText.getText().toString();
                RequestController.getInstance().setLogin(login);
                displayLoading();
                RequestController.getInstance().post(LoginActivity.this, "/login",
                        "login", login,
                        "password", pass);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("test", "login on resume");
        RequestController.getInstance().stopAllRequest();
        displayContent();
    }

    public void displayContent() {
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        EditText loginText = (EditText) findViewById(R.id.login);
        EditText passwordText = (EditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.button);
        bar.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        loginText.setVisibility(View.VISIBLE);
        passwordText.setVisibility(View.VISIBLE);
    }

    public void displayLoading() {
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        EditText loginText = (EditText) findViewById(R.id.login);
        EditText passwordText = (EditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.button);
        bar.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        loginText.setVisibility(View.INVISIBLE);
        passwordText.setVisibility(View.INVISIBLE);
    }

    public void onError() {
        onResume();
        Context context = getApplicationContext();
        CharSequence text = "Impossible de vous connecter\nveuillez réessayer";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
