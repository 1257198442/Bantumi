package es.upm.miw.bantumi.db;

import android.provider.BaseColumns;

public class HistoriaContract {
    private HistoriaContract(){};
    public static abstract class tableHistoria implements BaseColumns{
        static final String TABLE_NAME                ="Historia";

        static final String COL_NAME_ID               =_ID;
        static final String COL_NAME_JUEGO_1_NOMBRE   ="juego1Nombre";
        static final String COL_NAME_JUEGO_1_NUMERO   ="juego1Numero";
        static final String COL_NAME_JUEGO_2_NOMBRE   ="juego2Nombre";
        static final String COL_NAME_JUEGO_2_NUMERO   ="juego2Numero";
        static final String COL_NAME_GANADORES        ="ganadores";
        static final String COL_NAME_GANEDORESNUMERO  ="ganadoresNumero";
        static final String COL_NAME_TIEMPO           ="tiempo";
    }
}
