package com.cely404.nomad.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.cely404.nomad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultDialogFragment extends DialogFragment {

    /* The activity that creates an instance of this dialog fragment must
    * implement this interface in order to receive event callbacks.
    * Each method passes the DialogFragment in case the host needs to query it. */
    public interface SearchResultDialogListener {
        void onDialogPositiveClick();
        void onDialogNeutralClick();
    }

    //This instance of the Dialog Listener will be used to deliver action events
    SearchResultDialogListener mListener;


    public SearchResultDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        try {
            SearchResultDialogListener mHost = (SearchResultDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getTargetFragment()
                    .getClass().toString()
                    + "must implement SearchResultDialogListenerInterface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle SavedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.add_to_playlist, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((SearchResultDialogListener)getTargetFragment()).onDialogPositiveClick();

                    }
                })
                .setNeutralButton(R.string.view, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((SearchResultDialogListener)getTargetFragment()).onDialogNeutralClick();
                    }
                })
                .setNegativeButton(R.string.cancel, null);
        return builder.create();
    }


}
