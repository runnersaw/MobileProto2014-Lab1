package com.myapp.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class myFragment extends android.app.Fragment {

    public myFragment() {
    }

    public void resetText() {
        final ListView myListView = (ListView) getView().findViewById(R.id.listView);

        final ArrayList<String> ar = new ArrayList<String>();

        myListView.setAdapter(new chatAdapter(getActivity(), R.layout.chat_layout, ar));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        final Button myButton = (Button) rootView.findViewById(R.id.my_button);
        final TextView textView = (TextView) rootView.findViewById(R.id.hello_world);
        final ListView myListView = (ListView) rootView.findViewById(R.id.listView);
        final EditText editText = (EditText) rootView.findViewById(R.id.edit_message);

        final ArrayList<String> ar = new ArrayList<String>();

        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText(R.string.alt_text);
                String enteredText = editText.getText().toString();
                if (!enteredText.equals("")) {
                    ar.add(enteredText);
                    editText.setText("");
                    myListView.setAdapter(new chatAdapter(getActivity(), R.layout.chat_layout, ar));
                }
            }
        });

        myListView.setAdapter(new chatAdapter(getActivity(), R.layout.chat_layout, ar));
        return rootView;
    }
}
