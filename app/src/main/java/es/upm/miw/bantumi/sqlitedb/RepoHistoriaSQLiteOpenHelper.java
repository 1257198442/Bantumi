//package es.upm.miw.bantumi.sqlitedb;
//
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.COL_NAME_GANADORES;
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.COL_NAME_GANEDORESNUMERO;
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.COL_NAME_ID;
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.COL_NAME_JUEGO_1_NOMBRE;
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.COL_NAME_JUEGO_1_NUMERO;
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.COL_NAME_JUEGO_2_NOMBRE;
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.COL_NAME_JUEGO_2_NUMERO;
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.COL_NAME_TIEMPO;
//import static es.upm.miw.bantumi.sqlitedb.HistoriaContract.tableHistoria.TABLE_NAME;
//
//import android.annotation.SuppressLint;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.DatabaseUtils;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//
//public class RepoHistoriaSQLiteOpenHelper extends SQLiteOpenHelper {
//    private static final String DB_NAME =TABLE_NAME+".db";
//
//    private static final int DB_VERSION = 1;
//
//    public RepoHistoriaSQLiteOpenHelper(Context contexto){
//        super(contexto, DB_NAME, null, DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String consultaSQL = " CREATE TABLE " + TABLE_NAME+" ( "
//                +COL_NAME_ID                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                +COL_NAME_JUEGO_1_NOMBRE    +" TEXT, "
//                +COL_NAME_JUEGO_1_NUMERO    +" INTEGER, "
//                +COL_NAME_JUEGO_2_NOMBRE    +" TEXT, "
//                +COL_NAME_JUEGO_2_NUMERO    +" INTEGER, "
//                +COL_NAME_GANADORES         +" TEXT, "
//                +COL_NAME_GANEDORESNUMERO   +" INTEGER, "
//                +COL_NAME_TIEMPO            +" TEXT)";
//        sqLiteDatabase.execSQL(consultaSQL);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String consulyaSQL = " DROP TABLE IF EXISTS " + TABLE_NAME;
//        db.execSQL(consulyaSQL);
//        onCreate(db);
//    }
//
//    public void add(String juego1Nombre,  int juego1Numero, String juego2Nombre,  int juego2Numero, String ganadores,  int ganadoresNumero, String tiempo){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_NAME_JUEGO_1_NOMBRE  ,juego1Nombre);
//        values.put(COL_NAME_JUEGO_1_NUMERO  ,juego1Numero);
//        values.put(COL_NAME_JUEGO_2_NOMBRE  ,juego2Nombre);
//        values.put(COL_NAME_JUEGO_2_NUMERO  ,juego2Numero);
//        values.put(COL_NAME_GANADORES       ,ganadores);
//        values.put(COL_NAME_GANEDORESNUMERO ,ganadoresNumero);
//        values.put(COL_NAME_TIEMPO ,tiempo);
//        db.insert(TABLE_NAME,null,values);
//    }
//
//    public ArrayList<Historia> getAll(){
//        String consultaSQL = " SELECT * FROM " + TABLE_NAME + " WHERE  " + COL_NAME_ID + "  <= 10 ORDER BY " + COL_NAME_GANEDORESNUMERO + " DESC ";
//        ArrayList<Historia> historiaList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(consultaSQL,null);
//
//        if (cursor.moveToFirst()){
//            while (!cursor.isAfterLast()){
//                @SuppressLint("Range") Historia historia= new Historia(
//                        cursor.   getInt(cursor.getColumnIndex(COL_NAME_ID             )),
//                        cursor.getString(cursor.getColumnIndex(COL_NAME_JUEGO_1_NOMBRE )),
//                        cursor.   getInt(cursor.getColumnIndex(COL_NAME_JUEGO_1_NUMERO )),
//                        cursor.getString(cursor.getColumnIndex(COL_NAME_JUEGO_2_NOMBRE )),
//                        cursor.   getInt(cursor.getColumnIndex(COL_NAME_JUEGO_2_NUMERO )),
//                        cursor.getString(cursor.getColumnIndex(COL_NAME_GANADORES      )),
//                        cursor.getInt(cursor.getColumnIndex(COL_NAME_GANEDORESNUMERO)),
//                        cursor.getString(cursor.getColumnIndex(COL_NAME_TIEMPO))
//
//                );
//                historiaList.add(historia);
//                cursor.moveToNext();
//            }
//        }
//        cursor.close();
//        db.close();
//        return historiaList;
//    }
//
//    public long count() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
//    }
//
//    public void delete(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        db.delete(TABLE_NAME,null,null);
//    }
//}
