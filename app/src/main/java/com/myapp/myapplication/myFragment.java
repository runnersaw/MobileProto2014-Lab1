package com.myapp.myapplication;

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
import java.util.Arrays;

public class myFragment extends android.app.Fragment {

    public myFragment() {
    }

    public void resetText() {
        final ListView myListView = (ListView) getView().findViewById(R.id.listView);

        final ArrayList<String> ar = new ArrayList<String>();

        myListView.setAdapter(new chatAdapter(getActivity(), R.layout.chat_layout, ar));
    }

    public ArrayList<String> getStringArrayFromEntries(ArrayList<chatEntry> entries) {
        ArrayList<String> stringArray = new ArrayList<String>();
        int size = entries.size();
        for (int i=0; i<size; i++) {
            stringArray.add(entries.get(i).user+": "+entries.get(i).text);
        }
        return stringArray;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String username = ((MyActivity)getActivity()).getUser();
        final HandlerDatabase database = new HandlerDatabase(getActivity());
        database.open();
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        final Button myButton = (Button) rootView.findViewById(R.id.my_button);
        final TextView textView = (TextView) rootView.findViewById(R.id.hello_world);
        final ListView myListView = (ListView) rootView.findViewById(R.id.listView);
        final EditText usernameText = (EditText) rootView.findViewById(R.id.username_message);
        final EditText editText = (EditText) rootView.findViewById(R.id.edit_message);

        final ArrayList<chatEntry> chatEntries = database.getAllEntries();
        final ArrayList<String> ar = getStringArrayFromEntries(chatEntries);
        chatAdapter myListAdapter = new chatAdapter(getActivity(), R.layout.chat_layout, ar);
        myListView.setAdapter(myListAdapter);
        myListView.setSelection(myListAdapter.getCount() - 1);

        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText(R.string.alt_text);
                String enteredText = editText.getText().toString();
                if (!enteredText.equals("")) {
                    database.addEntryToDatabase(username, enteredText);
                    ar.add(username + ": " + enteredText);
                    editText.setText("");
                    chatAdapter myListAdapter = new chatAdapter(getActivity(), R.layout.chat_layout, ar);
                    myListView.setAdapter(myListAdapter);
                    myListView.setSelection(myListAdapter.getCount() - 1);
                }
            }
        });

        myListView.setAdapter(new chatAdapter(getActivity(), R.layout.chat_layout, ar));
        return rootView;
    }
}
