package com.myapp.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by svaughan on 9/15/14.
 */
public class usernameFragment extends android.app.Fragment {

    public usernameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_username, container, false);
        final Button myButton = (Button) rootView.findViewById(R.id.username_button);
        final EditText usernameText = (EditText) rootView.findViewById(R.id.username_message);

        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                if (!username.equals("")) {
                    ((MyActivity)getActivity()).setUser(username);
                    ((MyActivity)getActivity()).changeActivityToChat();
                }
            }
        });

        return rootView;
    }
}
