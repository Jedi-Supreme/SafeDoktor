package com.softedge.safedoktor.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.softedge.safedoktor.interfaces.safeDoktor_Access_Obj;
import com.softedge.safedoktor.models.swaggerModels.Appointmenttbl;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;

@Database(
        entities = {
                PatientModel.class,
                Appointmenttbl.class},
        exportSchema = false, version = 1)
public abstract class appDB extends RoomDatabase {

    private static appDB appDB_instance;

    public static appDB getInstance(Context context){

        if (appDB_instance == null){

            String DB_NAME = "safdoktorRoom_DB";
            appDB_instance = Room.databaseBuilder(context.getApplicationContext(),appDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        return appDB_instance;
    }

    public abstract safeDoktor_Access_Obj safeDoktorAccessObj();
}
