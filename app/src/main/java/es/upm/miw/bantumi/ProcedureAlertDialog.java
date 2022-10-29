package es.upm.miw.bantumi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import es.upm.miw.bantumi.MainActivity;

public class ProcedureAlertDialog extends AppCompatDialogFragment {

    interface SuccessCallback {
        void onSucces();
    }

    SuccessCallback success;
    String title;
    String message;

    public ProcedureAlertDialog(SuccessCallback success, String title, String message) {
        this.success = success;
        this.title = title;
        this.message = message;
    }

    @NonNull
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState){

        final MainActivity mainActivity =(MainActivity) getActivity();

        assert mainActivity != null;

        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                 "Si",
                 (Bantumidialog, which) -> success.onSucces()
                )
            .setNegativeButton(
                 "No",
                 (Bantumidialog, which) ->{}
                );
        return builder.create();
    }


}
