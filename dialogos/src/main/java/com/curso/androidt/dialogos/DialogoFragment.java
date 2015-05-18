package com.curso.androidt.dialogos;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogoFragment extends DialogFragment {

    private DialogInterface.OnClickListener negativeButtonListener;
    public void setNegativeButtonListener(DialogInterface.OnClickListener listener) {

    }

    public DialogoFragment() {
        // Required empty public constructor
//        show(getFragmentManager(), null);


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Desea continuar?");
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), "Se pulsó el No", Toast.LENGTH_LONG).show();
//            }
//        });

        builder.setNegativeButton("No", this.negativeButtonListener);
        builder.setNegativeButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Se pulsó el Si", Toast.LENGTH_LONG).show();
            }
        });
        return builder.create();
    }


}
