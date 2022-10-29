package es.upm.miw.bantumi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.upm.miw.bantumi.db.Historia;
import es.upm.miw.bantumi.db.RepoHistoriaSQLiteOpenHelper;
import es.upm.miw.bantumi.entity.DatodeTablero;
import es.upm.miw.bantumi.entity.SettingEntity;
import es.upm.miw.bantumi.model.BantumiViewModel;
import es.upm.miw.bantumi.activity.HistoriaListActivity;
import es.upm.miw.bantumi.activity.SettingActivity;

public class MainActivity extends AppCompatActivity {

    public static RepoHistoriaSQLiteOpenHelper db;
    protected final String LOG_TAG = "MiW";
    JuegoBantumi juegoBantumi;
    BantumiViewModel bantumiVM;
    int numInicialSemillas;


    SettingEntity settingEntity;
    Historia historia;


    boolean hasChange = false;
    boolean havaInitialled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setting
        settingEntity = SettingActivity.getSetting(this);
        // Instancia el ViewModel y el juego, y asigna observadores a los huecos
        numInicialSemillas = getResources().getInteger(R.integer.intNumInicialSemillas);
        bantumiVM = new ViewModelProvider(this).get(BantumiViewModel.class);
        juegoBantumi = new JuegoBantumi(bantumiVM, JuegoBantumi.Turno.turnoJ1, numInicialSemillas);
        db = new RepoHistoriaSQLiteOpenHelper(getApplicationContext());
        crearObservadores();
    }

    /**
     * Crea y subscribe los observadores asignados a las posiciones del tablero.
     * Si se modifica el contenido del tablero -> se actualiza la vista.
     */
    private void crearObservadores() {
        for (int i = 0; i < JuegoBantumi.NUM_POSICIONES; i++) {
            int finalI = i;
            bantumiVM.getNumSemillas(i).observe(    // Huecos y almacenes
                    this,
                    integer -> {
                        mostrarValor(finalI, juegoBantumi.getSemillas(finalI));
                        if (havaInitialled) hasChange = true;
                    });
        }
        bantumiVM.getTurno().observe(   // Turno
                this,
                turno -> {
                    marcarTurno(juegoBantumi.turnoActual());
                    if (!havaInitialled) havaInitialled = true;
                }
        );
    }

    /**
     * Indica el turno actual cambiando el color del texto
     *
     * @param turnoActual turno actual
     */
    private void marcarTurno(@NonNull JuegoBantumi.Turno turnoActual) {
        TextView tvJugador1 = findViewById(R.id.tvPlayer1);
        TextView tvJugador2 = findViewById(R.id.tvPlayer2);
        if(settingEntity.jugador1_nombre != null && !settingEntity.jugador1_nombre.isEmpty()) tvJugador1.setText(settingEntity.jugador1_nombre);
        if(settingEntity.jugador2_nombre != null && !settingEntity.jugador2_nombre.isEmpty()) tvJugador2.setText(settingEntity.jugador2_nombre);
        switch (turnoActual) {
            case turnoJ1:
                tvJugador1.setTextColor(getColor(R.color.white));
                tvJugador1.setBackgroundColor(getColor(android.R.color.holo_blue_light));
                tvJugador2.setTextColor(getColor(R.color.black));
                tvJugador2.setBackgroundColor(getColor(R.color.white));
                break;
            case turnoJ2:
                tvJugador1.setTextColor(getColor(R.color.black));
                tvJugador1.setBackgroundColor(getColor(R.color.white));
                tvJugador2.setTextColor(getColor(R.color.white));
                tvJugador2.setBackgroundColor(getColor(android.R.color.holo_blue_light));
                break;
            default:
                tvJugador1.setTextColor(getColor(R.color.black));
                tvJugador2.setTextColor(getColor(R.color.black));
        }
    }

    /**
     * Muestra el valor <i>valor</i> en la posición <i>pos</i>
     *
     * @param pos posición a actualizar
     * @param valor valor a mostrar
     */
    private void mostrarValor(int pos, int valor) {
        String num2digitos = String.format(Locale.getDefault(), "%02d", pos);
        // Los identificadores de los huecos tienen el formato casilla_XX
        int idBoton = getResources().getIdentifier("casilla_" + num2digitos, "id", getPackageName());
        if (0 != idBoton) {
            TextView viewHueco = findViewById(idBoton);
            viewHueco.setText(String.valueOf(valor));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opciones_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.opcAjustes: // @todo Preferencias
//                startActivity(new Intent(this, BantumiPrefs.class));
//                return true;
            case R.id.opcAcercaDe://rules
                new AlertDialog.Builder(this)
                        .setTitle(R.string.aboutTitle)
                        .setMessage(R.string.aboutMessage)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
                return true;

            case R.id.opcReiniciarPartida:
                //Restart
                ProcedureAlertDialog.SuccessCallback callback = () ->{
                    hasChange = false;
                    havaInitialled = false;
                    bantumiVM.inicializar();
                    juegoBantumi.inicializa(JuegoBantumi.Turno.turnoJ1);
                    crearObservadores();
                };
                if (!hasChange){
                    callback.hashCode();
                }else {
                    new ProcedureAlertDialog(callback,"Has iniciado el juego","¿Desea reiniciar otra partida?").show(getSupportFragmentManager(),"ALERT_DIALOG");
                }
                return true;

            case R.id.opcMejoresResultados:
                //History
                Intent intent = new Intent(MainActivity.this, HistoriaListActivity.class);
                startActivity(intent);
                return true;

            case R.id.opcAjustes:
                //Change player name
                Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(settingIntent);
                return true;

            case R.id.opcGuardarPartida:
                //Save
                List<DatodeTablero> tableros = new ArrayList<>();
                for(int sum=0;sum<JuegoBantumi.NUM_POSICIONES;sum++){
                    DatodeTablero datodeTablero = new DatodeTablero(sum,juegoBantumi.getSemillas(sum));
                    tableros.add(datodeTablero);
                }
                Gson g = new Gson();
                String jsonDatoDeTablero = g.toJson(tableros);
                try {
                    FileOutputStream fileOutputStream = openFileOutput("BantumiDato.json",MODE_PRIVATE);
                    fileOutputStream.write(jsonDatoDeTablero.getBytes(StandardCharsets.UTF_8));
                    Snackbar.make(findViewById(R.id.opcGuardarPartida),"Guardado con éxito",Snackbar.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Snackbar.make(findViewById(R.id.opcGuardarPartida),"Error"+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                }
                return true;

            case R.id.opcRecuperarPartida:
                //Resume the game
                ProcedureAlertDialog.SuccessCallback successCallback = () -> {
                    try {
                        FileInputStream fileInputStream = openFileInput("BantumiDato.json");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuilder stringBuilder = new StringBuilder();
                        String json;
                        while ((json = bufferedReader.readLine()) != null) {
                            stringBuilder.append(json);
                        }
                        inputStreamReader.close();
                        String jsonDato = stringBuilder.toString();
                        Gson gson = new Gson();
                        DatodeTablero[] datodeTableros = gson.fromJson(jsonDato, DatodeTablero[].class);
                        for (DatodeTablero datodeTablero : datodeTableros) {
                            juegoBantumi.setSemillas(datodeTablero.numero_de_piezas, datodeTablero.ubicacion);
                        }
                        Snackbar.make(findViewById(R.id.tvPlayer1), "El juego ha respondido", Snackbar.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
                if (hasChange){
                    new ProcedureAlertDialog(successCallback,"Has iniciado el juego","¿Desea reiniciar otra partida?").show(getSupportFragmentManager(),"ALERT_DIALOG");
                }else {
                    successCallback.onSucces();
                }
                return true;

// @TODO!!! resto opciones
            default:
                Snackbar.make(
                        findViewById(android.R.id.content),
                        getString(R.string.txtSinImplementar),
                        Snackbar.LENGTH_LONG
                ).show();
        }
        return true;
    }

    /**
     * Acción que se ejecuta al pulsar sobre un hueco
     *
     * @param v Vista pulsada (hueco)
     */
    public void huecoPulsado(@NonNull View v) {

        String resourceName = getResources().getResourceEntryName(v.getId()); // pXY
        int num = Integer.parseInt(resourceName.substring(resourceName.length() - 2));

        Log.i(LOG_TAG, "huecoPulsado(" + resourceName + ") num=" + num);
        switch (juegoBantumi.turnoActual()) {
            case turnoJ1:
                juegoBantumi.jugar(num);
                break;
            case turnoJ2:
                juegaComputador();
                break;
            default:    // JUEGO TERMINADO
                finJuego();
        }
        if (juegoBantumi.juegoTerminado()) {
            finJuego();
        }
    }

    /**
     * Elige una posición aleatoria del campo del jugador2 y realiza la siembra
     * Si mantiene turno -> vuelve a jugar
     */
    void juegaComputador() {

        while (juegoBantumi.turnoActual() == JuegoBantumi.Turno.turnoJ2) {
            int pos = 7 + (int) (Math.random() * 6);    // posición aleatoria
            Log.i(LOG_TAG, "juegaComputador(), pos=" + pos);
            if (juegoBantumi.getSemillas(pos) != 0 && (pos < 13)) {
                juegoBantumi.jugar(pos);
            } else {
                Log.i(LOG_TAG, "\t posición vacía");
            }
        }
    }

    /**
     * El juego ha terminado. Volver a jugar?
     */
    private void finJuego() {

        TextView tvJugador1 = findViewById(R.id.tvPlayer1);
        TextView tvJugador2 = findViewById(R.id.tvPlayer2);

        String texto = (juegoBantumi.getSemillas(6) > 6 * numInicialSemillas)
                ? "Gana " + tvJugador1.getText().toString()
                : "Gana " + tvJugador2.getText().toString();
        Snackbar.make(
                findViewById(android.R.id.content),
                texto,
                Snackbar.LENGTH_LONG
        )
        .show();
       // @TODO guardar puntuación
        new FinalAlertDialog(() -> {
            Historia historia = new Historia(tvJugador1.getText().toString(), juegoBantumi.getSemillas(6), tvJugador2.getText().toString(), juegoBantumi.getSemillas(13), getDate());
            db.add(historia.getJuego1Nombre(), historia.getJuego1Numero(), historia.getJuego2Nombre(), historia.getJuego2Numero(), historia.getGanadores(), historia.getGanadoresNumero(), historia.getTiempo());
        }).show(getSupportFragmentManager(), "ALERT_DIALOG");
    }

    public String getDate(){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}