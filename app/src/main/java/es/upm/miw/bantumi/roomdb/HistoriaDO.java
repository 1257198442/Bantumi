package es.upm.miw.bantumi.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Historia")
public class HistoriaDO {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "juego1Nombre")
    public String juego1Nombre;

    @ColumnInfo(name = "juego1Numero")
    public int juego1Numero;

    @ColumnInfo(name = "juego2Nombre")
    public String juego2Nombre;

    @ColumnInfo(name = "juego2Numero")
    public int juego2Numero;

    @ColumnInfo(name = "ganadores")
    public String ganadores;

    @ColumnInfo(name = "ganadoresNumero")
    public int ganadoresNumero;

    @ColumnInfo(name = "tiempo")
    public String tiempo;

    public HistoriaDO(String juego1Nombre, int juego1Numero, String juego2Nombre, int juego2Numero, String tiempo) {
        this.juego1Nombre = juego1Nombre;
        this.juego1Numero = juego1Numero;
        this.juego2Nombre = juego2Nombre;
        this.juego2Numero = juego2Numero;
        this.ganadores = juego1Numero > juego2Numero?juego1Nombre:juego2Nombre;
        this.ganadoresNumero = Math.max(juego1Numero, juego2Numero);
        this.tiempo = tiempo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJuego1Nombre() {
        return juego1Nombre;
    }

    public void setJuego1Nombre(String juego1Nombre) {
        this.juego1Nombre = juego1Nombre;
    }

    public int getJuego1Numero() {
        return juego1Numero;
    }

    public void setJuego1Numero(int juego1Numero) {
        this.juego1Numero = juego1Numero;
    }

    public String getJuego2Nombre() {
        return juego2Nombre;
    }

    public void setJuego2Nombre(String juego2Nombre) {
        this.juego2Nombre = juego2Nombre;
    }

    public int getJuego2Numero() {
        return juego2Numero;
    }

    public void setJuego2Numero(int juego2Numero) {
        this.juego2Numero = juego2Numero;
    }

    public String getGanadores() {
        return ganadores;
    }

    public void setGanadores(String ganadores) {
        this.ganadores = ganadores;
    }

    public int getGanadoresNumero() {
        return ganadoresNumero;
    }

    public void setGanadoresNumero(int ganadoresNumero) {
        this.ganadoresNumero = ganadoresNumero;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
