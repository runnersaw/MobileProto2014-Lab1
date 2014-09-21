package com.myapp.myapplication;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;


public class MyActivity extends Activity {

    public String user;
    public boolean onChat;

    public MyActivity() {
        this.onChat = false;
    }

    public void setUser(String username) {
        this.user = username;
    }

    public String getUser() {
        return this.user;
    }

    public void changeActivityToChat() {
        Fragment fragment = new myFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, fragment, "myFragment");
        transaction.commit();
        this.onChat = true;
    }

    public void changeUsername() {
        DialogFragment newFragment = new usernameDialogFragment();
        newFragment.show(getFragmentManager(), "missiles");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new usernameFragment(), "usernameFragment")
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.reset_text && this.onChat) {
            myFragment frag = (myFragment) getFragmentManager().findFragmentByTag("myFragment");
            frag.resetText();
        }
        if (id == R.id.reset_user && this.onChat) {
            changeUsername();
        }
        return super.onOptionsItemSelected(item);
    }
}
