package es.upm.miw.bantumi.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = HistoriaDO.class, version = 3, exportSchema = true)
public abstract class HistoriaDataBase extends RoomDatabase {

    public abstract HistoriaDao getHistoriaDao();

    private static final String DB_NAME = "HistoriaDatabase.db";
    private static volatile HistoriaDataBase instance;

    public static synchronized HistoriaDataBase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static HistoriaDataBase create(final Context context) {
        return Room.databaseBuilder(context, HistoriaDataBase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
