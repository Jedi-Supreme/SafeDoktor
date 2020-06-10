package com.softedge.safedoktor.models.swaggerModels.body;

import androidx.room.Entity;
import androidx.room.Index;

import com.softedge.safedoktor.models.swaggerModels.response.BasicObject;

import static com.softedge.safedoktor.utilities.AppConstants.KEY_ID;
import static com.softedge.safedoktor.utilities.AppConstants.TABLE_SPECIALTIES;

@Entity(
        tableName = TABLE_SPECIALTIES,
        primaryKeys = {KEY_ID},
        indices = {@Index(value = KEY_ID,unique = true)}
        )
public class Specialties extends BasicObject {

    private boolean isChecked;
    public Specialties(int id, String name) {
        super(id, name);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
