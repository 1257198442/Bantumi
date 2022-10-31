package es.upm.miw.bantumi.sqlitedb;

import androidx.annotation.NonNull;

public class Historia {

    private int id;
    private String juego1Nombre;
    private int juego1Numero;
    private String juego2Nombre;
    private int juego2Numero;
    private String ganadores;
    private int ganadoresNumero;
    private String tiempo;

    public Historia() {
    }

    public Historia(int id, String juego1Nombre, int juego1Numero, String juego2Nombre, int juego2Numero, String ganadores, int ganadoresNumero, String tiempo) {
        this.id = id;
        this.juego1Nombre = juego1Nombre;
        this.juego1Numero = juego1Numero;
        this.juego2Nombre = juego2Nombre;
        this.juego2Numero = juego2Numero;
        this.ganadores = ganadores;
        this.ganadoresNumero = ganadoresNumero;
        this.tiempo = tiempo;
    }

    public Historia(String juego1Nombre, int juego1Numero, String juego2Nombre, int juego2Numero, String tiempo) {
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

    public void setJuego1Numero( int juego1Numero) {
        this.juego1Numero = juego1Numero;
    }

    public String getJuego2Nombre() {
        return juego2Nombre;
    }

    public void setJuego2Nombre(String juego2Nombre) {
        this.juego2Nombre = juego2Nombre;
    }

    public  int getJuego2Numero() {
        return juego2Numero;
    }

    public void setJuego2Numero( int juego2Numero) {
        this.juego2Numero = juego2Numero;
    }

    public String getGanadores() {
        return ganadores;
    }

    public void setGanadores(String ganadores) {
        this.ganadores = ganadores;
    }

    public  int getGanadoresNumero() {
        return ganadoresNumero;
    }

    public void setGanadoresNumero( int ganadoresNumero) {
        this.ganadoresNumero = ganadoresNumero;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    @NonNull
    @Override
    public String toString() {
        return "Historia{" +
                "id=" + id +
                ", juego1Nombre='" + juego1Nombre + '\'' +
                ", juego1Numero=" + juego1Numero +
                ", juego2Nombre='" + juego2Nombre + '\'' +
                ", juego2Numero=" + juego2Numero +
                ", ganadores='" + ganadores + '\'' +
                ", ganadoresNumero=" + ganadoresNumero +
                ", tiempo='" + tiempo + '\'' +
                '}';
    }
}
