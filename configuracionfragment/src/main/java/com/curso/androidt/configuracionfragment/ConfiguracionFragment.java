package com.curso.androidt.configuracionfragment;


import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracionFragment extends PreferenceFragment {


    public ConfiguracionFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
