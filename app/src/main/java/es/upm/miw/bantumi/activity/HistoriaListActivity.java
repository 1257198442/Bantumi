package es.upm.miw.bantumi.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.upm.miw.bantumi.component.ComparedComponent;
import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.roomdb.HistoriaDO;
import es.upm.miw.bantumi.roomdb.HistoriaDao;
import es.upm.miw.bantumi.roomdb.HistoriaDataBase;
import es.upm.miw.bantumi.sqlitedb.Historia;
import es.upm.miw.bantumi.sqlitedb.RepoHistoriaSQLiteOpenHelper;

public class HistoriaListActivity extends AppCompatActivity {
    //sqlite
//    RepoHistoriaSQLiteOpenHelper db;
//    List<Historia> list;
    HistoriaDao historiaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historia_list);

        //sqlite
//        this.db = new RepoHistoriaSQLiteOpenHelper(getApplicationContext());
//        list = this.db.getAll();
        historiaDao = HistoriaDataBase.getInstance(this).getHistoriaDao();
        List<HistoriaDO> list = historiaDao.getAll();
        load(list);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.setting_opcions_menu, menu);
        return true;
    }

    @SuppressLint("SetTextI18n")
    //sqlite
//    public void load(@Nullable List<Historia> list) {
    public void load(@Nullable List<HistoriaDO> list) {
        LinearLayout hlist = findViewById(R.id.list);
        hlist.removeAllViews();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ComparedComponent item = new ComparedComponent(this);
                hlist.addView(item);
                item.setPlayer1Nombre(list.get(i).getJuego1Nombre());
                item.setJuego1Numero(String.valueOf(list.get(i).getJuego1Numero()));
                item.setPlayer2Nombre(list.get(i).getJuego2Nombre());
                item.setJuego2Numero(String.valueOf(list.get(i).getJuego2Numero()));
                item.setGanadoresNombre(list.get(i).getGanadores());
                item.setGanadoresNumero(String.valueOf(list.get(i).getGanadoresNumero()));
                item.settiempo(list.get(i).getTiempo());
            }
        } else {
            TextView text = new TextView(this);
            text.setText(R.string.warning);
            text.setTextSize(22);
            text.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            text.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = 100;
            text.setLayoutParams(params);
            hlist.addView(text);
        }
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.opcBorrarResultados) {
            //sqlite
//            db.delete();
            historiaDao = HistoriaDataBase.getInstance(this).getHistoriaDao();
            historiaDao.deleteAll();
            load(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
