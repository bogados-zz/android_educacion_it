package com.android.ejemplofragmentos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ejemplofragmentos.R;

/**
 * Created by sbogado on 18/10/16.
 */

public class DetalleFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle){
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }


}
