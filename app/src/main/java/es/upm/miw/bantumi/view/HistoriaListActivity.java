package es.upm.miw.bantumi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.bantumi.ComparedComponent;
import es.upm.miw.bantumi.MainActivity;
import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.db.Historia;
import es.upm.miw.bantumi.db.RepoHistoriaSQLiteOpenHelper;

public class HistoriaListActivity extends AppCompatActivity {
    RepoHistoriaSQLiteOpenHelper db;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.historia_list);
    this.db = new RepoHistoriaSQLiteOpenHelper(getApplicationContext());
    List<Historia> list = this.db.getAll();
    load(list);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.setting_opcions_menu, menu);
        return true;
    }
public void load(@Nullable List<Historia> list) {
    LinearLayout hlist = findViewById(R.id.list);
    hlist.removeAllViews();
    if (list != null && list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
            ComparedComponent item = new ComparedComponent(this);
            hlist.addView(item);
            item.setPlayer1Nombre              (list.get(i).getJuego1Nombre());
            item.setJuego1Numero(String.valueOf(list.get(i).getJuego1Numero()));
            item.setPlayer2Nombre              (list.get(i).getJuego2Nombre());
            item.setJuego2Numero(String.valueOf(list.get(i).getJuego2Numero()));
            item.setGanadoresNombre            (list.get(i).getGanadores());
            item.setGanadoresNumero(String.valueOf(list.get(i).getGanadoresNumero()));
            item.settiempo                     (list.get(i).getTiempo());
        }
    } else {
        TextView text = new TextView(this);
        text.setText("No Hay Hoistoria");
        text.setTextSize(22);
        text.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        text.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = 80;
        text.setLayoutParams(params);
        hlist.addView(text);
        }
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcBorrarResultados:
                db.delete();
                load(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
