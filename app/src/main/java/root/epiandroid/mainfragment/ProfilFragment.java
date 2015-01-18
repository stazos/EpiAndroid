package root.epiandroid.mainfragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import root.epiandroid.MainActivity;
import root.epiandroid.R;
import root.epiandroid.controller.ProfilController;
import root.epiandroid.controller.RequestController;
import root.epiandroid.model.Message;
import root.epiandroid.request.GetRequest;
import root.epiandroid.request.ImageRequest;
import root.epiandroid.request.PostRequest;

/**
 * Created by vesy_m on 15/01/15.
 */
public class ProfilFragment extends AbstractObserverFragment {

    View rootView = null;

    GetRequest req1;
    PostRequest req2;
    GetRequest req3;
    ImageRequest req4;

    public ProfilFragment() {

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        List<Message> listMessages = (List<Message>) listArgs[6];

        Log.e("test", "updateProfil");
        Activity act = getActivity();
        if (error != null) {
            ProgressBar bar = (ProgressBar) act.findViewById(R.id.profil_progress);
            bar.setVisibility(View.INVISIBLE);

            TextView profilLogText = (TextView) act.findViewById(R.id.profil_error_text);
            profilLogText.setText(error);

            req1.cancel(true);
            req2.cancel(true);
            req3.cancel(true);
            req4.cancel(true);
            Button button = (Button) act.findViewById(R.id.profil_reload);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity act = getActivity();
                    ProgressBar bar = (ProgressBar) act.findViewById(R.id.profil_progress);


                    TextView profilLogText = (TextView) act.findViewById(R.id.profil_error_text);

                    Button button = (Button) act.findViewById(R.id.profil_reload);

                    profilLogText.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    bar.setVisibility(View.VISIBLE);
                    ProfilController.getInstance().profilReload();
                }
            });
            button.setVisibility(View.VISIBLE);
            profilLogText.setVisibility(View.VISIBLE);
        } else {
            if (pathPicture == null) {
                req1 = RequestController.getInstance().get(act, "/photo", "token", token, "login", login);
            }
            if (pathPicture != null && logTime == null && listMessages == null) {
                req2 = RequestController.getInstance().post(act, "/infos:log", "token", token);
                req3 = RequestController.getInstance().get(act, "/messages", "token", token);
                req4 = RequestController.getInstance().image(pathPicture);
            }
            if (picture != null && logTime != null && listMessages != null) {

                TextView profilLoginText = (TextView) act.findViewById(R.id.profil_login_text);
                profilLoginText.setText(login);

                TextView profilLogText = (TextView) act.findViewById(R.id.profil_log_text);
                profilLogText.setText("log:" + logTime);

                ImageView profilImage = (ImageView) act.findViewById(R.id.profil_image);
                profilImage.setImageBitmap(picture);

                ListView listview = (ListView) act.findViewById(R.id.profil_list);
                ArrayList<String> list = new ArrayList<String>();
                for (Message msg : listMessages) {
                    list.add(msg.getTitle());
                }
                ListAdapter adapter = new ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, list);
                listview.setAdapter(adapter);


                ProgressBar bar = (ProgressBar) act.findViewById(R.id.profil_progress);
                bar.setVisibility(View.INVISIBLE);
                profilLoginText.setVisibility(View.VISIBLE);
                profilLogText.setVisibility(View.VISIBLE);
                profilImage.setVisibility(View.VISIBLE);
                listview.setVisibility(View.VISIBLE);
            }
        }
    }
}
