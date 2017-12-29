package com.software.villaman.transitions;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "mainActivity";
    int mCurrentScence = 1;
    private Scene mScene1, mScene2;
    private ViewGroup sScenceRoot;
    private TransitionManager mManager;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sScenceRoot = (ViewGroup) findViewById(R.id.root_scene);
        mScene1 = Scene.getSceneForLayout(sScenceRoot, R.layout.scene1, this);
        mScene2 = Scene.getSceneForLayout(sScenceRoot, R.layout.scene2, this);
        mManager = TransitionInflater.from(this).inflateTransitionManager(R.transition.manager, sScenceRoot);
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void runTransition2(View view){
        if(mCurrentScence == 1){
            TransitionManager.go(mScene2);
            mCurrentScence = 2;
        } else {
            mManager.transitionTo(mScene1);
            mCurrentScence = 1;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void runTransition(View view) {
        if(mCurrentScence == 1){
            TransitionManager.go(mScene2);
            mCurrentScence = 2;
        } else {
            mManager.transitionTo(mScene1);
            mCurrentScence = 1;
        }
    }
}
