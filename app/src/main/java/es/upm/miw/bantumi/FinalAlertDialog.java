package es.upm.miw.bantumi;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


public class FinalAlertDialog extends AppCompatDialogFragment {

    SuccessCallback successCallbacks;
    interface SuccessCallback {
        void Success();
    }
    public FinalAlertDialog(SuccessCallback successCallback){
       successCallbacks = successCallback;
    }

    @SuppressLint("SuspiciousIndentation")
    @NonNull
    @Override
	public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
		final MainActivity main = (MainActivity) getActivity();
        setCancelable(false);
//        assert main != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder
            .setTitle(R.string.txtDialogoFinalTitulo)
            .setMessage(R.string.txtDialogoFinalPregunta)
            .setPositiveButton(
                getString(R.string.txtDialogoFinalAfirmativo),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            successCallbacks.Success();
                            main.juegoBantumi.inicializar(MainActivity.jugador);
                        }
                    }
            )
                .setNegativeButton(
                        getString(R.string.txtDialogoFinalNegativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                main.finish();
                            }
                        }
                );
		return builder.create();
	}
}
