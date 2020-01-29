package com.example.latestsenatorproj;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;


public class SenatorDatabaseWrapper extends CursorWrapper {
    public SenatorDatabaseWrapper(Cursor cursor) {
        super(cursor);
    }
    public Senator getSenator(){
        String name = getString(getColumnIndex(SenatorDbSchema.SenatorTable.Cols.NAME));
        String email = getString(getColumnIndex(SenatorDbSchema.SenatorTable.Cols.EMAIL));
        String phone = getString(getColumnIndex(SenatorDbSchema.SenatorTable.Cols.PHONE));
        String state = getString(getColumnIndexOrThrow(SenatorDbSchema.SenatorTable.Cols.STATE));

        Senator senator = new Senator(name);
        senator.setName(name);
        senator.setEmail(email);
        senator.setPhone(phone);
        senator.setState(state);
        return senator;
    }


    public Senator getSenatorById(){
        String id = getString(getColumnIndex(SenatorDbSchema.SenatorTable.Cols.UUID));
        String name = getString(getColumnIndex(SenatorDbSchema.SenatorTable.Cols.NAME));
        String email = getString(getColumnIndex(SenatorDbSchema.SenatorTable.Cols.EMAIL));
        String phone = getString(getColumnIndex(SenatorDbSchema.SenatorTable.Cols.PHONE));
        String state = getString(getColumnIndexOrThrow(SenatorDbSchema.SenatorTable.Cols.STATE));

        Senator senator = new Senator(UUID.fromString(id));

        senator.setName(name);
        senator.setEmail(email);
        senator.setPhone(phone);
        senator.setState(state);
        return senator;
    }
}
