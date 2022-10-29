package es.upm.miw.bantumi.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.entity.SettingEntity;

public class SettingActivity extends AppCompatActivity {

    EditText jugador1_nombre;
    EditText jugador2_nombre;

    public static SettingEntity getSetting(Context context) {
        try {
            FileInputStream fileInputStream = context.openFileInput("SettingSave.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String dato;
            while ((dato = bufferedReader.readLine())!=null){
                stringBuilder.append(dato);
            }
            inputStreamReader.close();
            String json = stringBuilder.toString();
            Gson gson = new Gson();
            return gson.fromJson(json,SettingEntity.class);
        }catch (Exception e){
            if(e instanceof FileNotFoundException){
                return new SettingEntity();
            }
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return new SettingEntity();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SettingEntity settingEntity = getSetting(this);

        jugador1_nombre = findViewById(R.id.jugador1nombre);
        jugador2_nombre = findViewById(R.id.jugador2nombre);
        jugador1_nombre.setHint(settingEntity.jugador1_nombre);
        jugador2_nombre.setHint(settingEntity.jugador2_nombre);

        Button button = findViewById(R.id.button);
        button.setOnClickListener((b) ->{
            settingEntity.jugador1_nombre = this.jugador1_nombre.getText().toString();
            settingEntity.jugador2_nombre = this.jugador2_nombre.getText().toString();

            Gson settingGson = new Gson();
            String settingJsonSave = settingGson.toJson(settingEntity);

            try {
                FileOutputStream fileOutputStream = openFileOutput("SettingSave.json",MODE_PRIVATE);
                fileOutputStream.write(settingJsonSave.getBytes(StandardCharsets.UTF_8));
                Snackbar.make(
                        findViewById(R.id.setting),
                        "Guardado con éxito",
                        Snackbar.LENGTH_SHORT
                ).show();
                fileOutputStream.close();
            } catch (IOException e) {
                Snackbar.make(
                        findViewById(R.id.setting),
                        Objects.requireNonNull(e.getMessage()),
                        Snackbar.LENGTH_SHORT
                ).show();
            }
        });
    }
}
