package com.myapp.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by svaughan on 9/11/14.
 */

public class chatAdapter extends ArrayAdapter<String> {

    public chatAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }
}
