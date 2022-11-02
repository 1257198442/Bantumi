package es.upm.miw.bantumi.roomdb;//package es.upm.miw.bantumi.db;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.RoomWarnings;

import java.util.List;


@Dao
public interface HistoriaDao {
    static final String TABLE_NAME                ="Historia";
    static final String COL_NAME_GANADORESNUMERO        ="ganadoresNumero";
    static final String COL_NAME_GANADORES        ="ganadores";
    static final String COL_NAME_JUEGO_1_NOMBRE = "juego1Nombre";
    static final String COL_NAME_JUEGO_2_NOMBRE       = "juego2Nombre";


    @Query(" SELECT * FROM " + TABLE_NAME + " ORDER BY "+ COL_NAME_GANADORESNUMERO + " DESC LIMIT 0,10 ")
    List<HistoriaDO> getAll();

    @Query(" SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NAME_GANADORES +" = :ganadores" + " ORDER BY "+ COL_NAME_GANADORESNUMERO + " DESC LIMIT 0,10 ")
    List<HistoriaDO> getByGanadores(String ganadores);

    @Query(" SELECT " +COL_NAME_JUEGO_1_NOMBRE + " FROM " + TABLE_NAME)
    List<String> getAllJugadores1();

    @Query(" SELECT " +COL_NAME_JUEGO_2_NOMBRE + " FROM " + TABLE_NAME)
    List<String> getAllJugadores2();
    @Insert
    void insert(HistoriaDO... historiasDO);

    @Delete
    void delete(HistoriaDO... historiasDO);

    @Query(" DELETE FROM "+TABLE_NAME)
    void deleteAll();
}
