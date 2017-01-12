package com.example.myscientificcalc;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class LM_Fragment extends Fragment
{
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {

        v=inflater.inflate(R.layout.lm_fragment, container, false);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.RunWhenFragmentIsReady();
        System.out.println("onResume in landscape");
    }
}