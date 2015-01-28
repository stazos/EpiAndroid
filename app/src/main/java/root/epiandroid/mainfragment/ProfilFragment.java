package root.epiandroid.mainfragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.controller.ProfilController;
import root.epiandroid.controller.RequestController;
import root.epiandroid.model.object.Message;

/**
 * Created by vesy_m on 15/01/15.
 */
public class ProfilFragment extends AbstractObserverFragment {

    View rootView = null;

    public ProfilFragment() {

    }

    public void displayContent() {
        Activity act = getActivity();
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.profil_progress);
        Button reload = (Button) act.findViewById(R.id.profil_reload);

        ImageView profilImage = (ImageView) act.findViewById(R.id.profil_image);

        TextView profilLoginText = (TextView) act.findViewById(R.id.profil_login_text);

        ListView listview = (ListView) act.findViewById(R.id.profil_list);

        TextView profilCurrentCredit = (TextView) act.findViewById(R.id.profil_current_credit_text);
        TextView profilInProgressCredit = (TextView) act.findViewById(R.id.profil_in_progress_credit_text);
        TextView profilFailCredit = (TextView) act.findViewById(R.id.profil_fail_credit_text);
        TextView profilSemesterNum = (TextView) act.findViewById(R.id.profil_semester_num_text);
        TextView profilPromo = (TextView) act.findViewById(R.id.profil_promo_text);
        TextView profilLog = (TextView) act.findViewById(R.id.profil_log_text);


        bar.setVisibility(View.INVISIBLE);
        reload.setVisibility(View.INVISIBLE);

        profilImage.setVisibility(View.VISIBLE);
        profilLoginText.setVisibility(View.VISIBLE);

        listview.setVisibility(View.VISIBLE);

        profilCurrentCredit.setVisibility(View.VISIBLE);
        profilInProgressCredit.setVisibility(View.VISIBLE);
        profilFailCredit.setVisibility(View.VISIBLE);
        profilSemesterNum.setVisibility(View.VISIBLE);
        profilPromo.setVisibility(View.VISIBLE);
        profilLog.setVisibility(View.VISIBLE);
    }

    public void displayLoading() {
        Activity act = getActivity();
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.profil_progress);
        Button reload = (Button) act.findViewById(R.id.profil_reload);

        ImageView profilImage = (ImageView) act.findViewById(R.id.profil_image);

        TextView profilLoginText = (TextView) act.findViewById(R.id.profil_login_text);

        ListView listview = (ListView) act.findViewById(R.id.profil_list);

        TextView profilCurrentCredit = (TextView) act.findViewById(R.id.profil_current_credit_text);
        TextView profilInProgressCredit = (TextView) act.findViewById(R.id.profil_in_progress_credit_text);
        TextView profilFailCredit = (TextView) act.findViewById(R.id.profil_fail_credit_text);
        TextView profilSemesterNum = (TextView) act.findViewById(R.id.profil_semester_num_text);
        TextView profilPromo = (TextView) act.findViewById(R.id.profil_promo_text);
        TextView profilLog = (TextView) act.findViewById(R.id.profil_log_text);

        reload.setVisibility(View.INVISIBLE);

        profilImage.setVisibility(View.INVISIBLE);
        profilLoginText.setVisibility(View.INVISIBLE);

        listview.setVisibility(View.INVISIBLE);

        profilCurrentCredit.setVisibility(View.INVISIBLE);
        profilInProgressCredit.setVisibility(View.INVISIBLE);
        profilFailCredit.setVisibility(View.INVISIBLE);
        profilSemesterNum.setVisibility(View.INVISIBLE);
        profilPromo.setVisibility(View.INVISIBLE);
        profilLog.setVisibility(View.INVISIBLE);

        bar.setVisibility(View.VISIBLE);
    }

    public void displayError() {
        Activity act = getActivity();
        ProgressBar bar = (ProgressBar) act.findViewById(R.id.profil_progress);
        Button reload = (Button) act.findViewById(R.id.profil_reload);

        ImageView profilImage = (ImageView) act.findViewById(R.id.profil_image);

        TextView profilLoginText = (TextView) act.findViewById(R.id.profil_login_text);

        ListView listview = (ListView) act.findViewById(R.id.profil_list);

        TextView profilCurrentCredit = (TextView) act.findViewById(R.id.profil_current_credit_text);
        TextView profilInProgressCredit = (TextView) act.findViewById(R.id.profil_in_progress_credit_text);
        TextView profilFailCredit = (TextView) act.findViewById(R.id.profil_fail_credit_text);
        TextView profilSemesterNum = (TextView) act.findViewById(R.id.profil_semester_num_text);
        TextView profilPromo = (TextView) act.findViewById(R.id.profil_promo_text);
        TextView profilLog = (TextView) act.findViewById(R.id.profil_log_text);

        bar.setVisibility(View.INVISIBLE);

        profilImage.setVisibility(View.INVISIBLE);
        profilLoginText.setVisibility(View.INVISIBLE);

        listview.setVisibility(View.INVISIBLE);

        profilCurrentCredit.setVisibility(View.INVISIBLE);
        profilInProgressCredit.setVisibility(View.INVISIBLE);
        profilFailCredit.setVisibility(View.INVISIBLE);
        profilSemesterNum.setVisibility(View.INVISIBLE);
        profilPromo.setVisibility(View.INVISIBLE);
        profilLog.setVisibility(View.INVISIBLE);

        reload.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profil, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.reload_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reload_option_menu:
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ProfilController.getInstance().profilReload();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        displayLoading();
        Activity act = getActivity();
        Button button = (Button) act.findViewById(R.id.profil_reload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestController.getInstance().stopAllRequest();
                displayLoading();
                ProfilController.getInstance().profilReload();
            }
        });
        ProfilController.getInstance().addObserver(this);
    }

    @Override
    public void update(Object... objs) {
        Object[] listArgs = objs.clone();
        String error = (String) listArgs[0];
        String token = (String) listArgs[1];
        String login = (String) listArgs[2];
        String pathPicture = (String) listArgs[3];
        Bitmap picture = (Bitmap) listArgs[4];
        String logTime = (String) listArgs[5];
        String title = (String) listArgs[6];
        String currentCredit = (String) listArgs[7];
        String failCredit = (String) listArgs[8];
        String inProgressCredit = (String) listArgs[9];
        String semesterNum = (String) listArgs[10];
        String promo = (String) listArgs[11];
        List<Message> listMessages = (List<Message>) listArgs[12];

        Log.e("test", "updateProfil");
        Activity act = getActivity();
        if (error != null) {
            RequestController.getInstance().stopAllRequest();
            displayError();

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(act, error, duration);
            toast.show();
        } else {
            if (pathPicture == null) {
                RequestController.getInstance().get(act, "/photo", "token", token, "login", login);
            }
            if (pathPicture != null && logTime == null && listMessages == null) {
                RequestController.getInstance().post(act, "/infos:log", "token", token);
                RequestController.getInstance().get(act, "/messages", "token", token);
                RequestController.getInstance().image(act, pathPicture);
            }
            if (picture != null && logTime != null && listMessages != null) {

                TextView profilLoginText = (TextView) act.findViewById(R.id.profil_login_text);
                profilLoginText.setText(title);

                TextView profilCurrentCredit = (TextView) act.findViewById(R.id.profil_current_credit_text);
                profilCurrentCredit.setText("Vous avez " + currentCredit + " crédits.");

                TextView profilInProgressCredit = (TextView) act.findViewById(R.id.profil_in_progress_credit_text);
                profilInProgressCredit.setText("Vous pouvez acquerir " + inProgressCredit + " crédits.");

                TextView profilFailCredit = (TextView) act.findViewById(R.id.profil_fail_credit_text);
                profilFailCredit.setText("Vous avez échouer " + failCredit + " crédits.");

                TextView profilSemesterNum = (TextView) act.findViewById(R.id.profil_semester_num_text);
                profilSemesterNum.setText("Vous êtes au semestre " + semesterNum + ".");

                TextView profilPromo = (TextView) act.findViewById(R.id.profil_promo_text);
                profilPromo.setText("Votre promotion est " + promo + ".");

                TextView profilLog = (TextView) act.findViewById(R.id.profil_log_text);
                profilLog.setText("Votre temps de log est de " + logTime + "h.");

                ImageView profilImage = (ImageView) act.findViewById(R.id.profil_image);
                profilImage.setImageBitmap(picture);

                ListView listview = (ListView) act.findViewById(R.id.profil_list);
                ArrayList<String> list = new ArrayList<String>();
                for (Message msg : listMessages) {
                    list.add(msg.getTitle());
                }
                ListAdapter adapter = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, list);
                listview.setAdapter(adapter);

                displayContent();
            }
        }
    }
}
