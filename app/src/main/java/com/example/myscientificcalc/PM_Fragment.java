package com.example.myscientificcalc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class PM_Fragment extends Fragment

{
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        v= inflater.inflate(R.layout.pm_fragment, container, false);

        return v;


    }

    @Override
    public void onResume(){
        super.onResume();
        MainActivity.RunWhenFragmentIsReady();
        System.out.println("onResume called");


    }
}