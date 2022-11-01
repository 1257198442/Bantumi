package es.upm.miw.bantumi.roomdb;//package es.upm.miw.bantumi.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface HistoriaDao {
    static final String TABLE_NAME                ="Historia";
    static final String COL_NAME_GANADORESNUMERO        ="ganadoresNumero";


    @Query(" SELECT * FROM " + TABLE_NAME + " ORDER BY "+ COL_NAME_GANADORESNUMERO + " DESC LIMIT 0,10 ")
    List<HistoriaDO> getAll();

    @Insert
    void insert(HistoriaDO... historiasDO);

    @Delete
    void delete(HistoriaDO... historiasDO);

    @Query(" DELETE FROM "+TABLE_NAME)
    void deleteAll();
}
