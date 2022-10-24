package es.upm.miw.bantumi;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ProcedureAlertDialog extends AppCompatDialogFragment {

    interface SuccessCallback {
        void succes();
    }

    SuccessCallback success;
    String title;
    String message;

    public ProcedureAlertDialog(SuccessCallback success, String title, String message) {
        this.success = success;
        this.title = title;
        this.message = message;
    }

    public AppCompatDialog onCreateDialog(Bundle savedInstanceState){

        final MainActivity mainActivity =(MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                 "Si",
                 (Bantumidialog, which) ->{
                 success.succes();
                 }
                )
            .setNegativeButton(
                 "No",
                 (Bantumidialog, which) ->{}
                );
        return builder.create();
    }


}
