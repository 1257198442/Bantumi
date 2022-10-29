package es.upm.miw.bantumi.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import es.upm.miw.bantumi.R;

public class ComparedComponent extends LinearLayout {

    private final View target;

    public ComparedComponent(Context context) {
        this(context, null);
    }

    public ComparedComponent(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ComparedComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        target = LayoutInflater.from(context).inflate(R.layout.historia, this);
    }

    public void setPlayer1Nombre(String name) {
        TextView player = target.findViewById(R.id.juego_1_Nombre);
        player.setText(name);
    }

    public void setJuego1Numero(String text) {
        TextView data = target.findViewById(R.id.juego_1_numero);
        data.setText(text);
    }

    public void setPlayer2Nombre(String name) {
        TextView player = target.findViewById(R.id.juego_2_Nombre);
        player.setText(name);
    }

    public void setJuego2Numero(String text) {
        TextView data = target.findViewById(R.id.juego_2_numero);
        data.setText(text);
    }

    public void setGanadoresNombre(String text) {
        TextView data = target.findViewById(R.id.ganadores_nombre);
        data.setText(text);
    }

    public void setGanadoresNumero(String text) {
        TextView data = target.findViewById(R.id.ganadores_numero);
        data.setText(text);
    }

    public void settiempo(String text) {
        TextView data = target.findViewById(R.id.tiempo);
        data.setText(text);
    }
}
