package  com.example.latestsenatorproj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import  com.example.latestsenatorproj.SenatorDbSchema.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SenatorSingleton {

    private static SenatorSingleton mSenatorSingleton;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static SenatorSingleton getInstance(Context context){

        if(mSenatorSingleton == null){

            mSenatorSingleton = new SenatorSingleton(context);
        }

        return mSenatorSingleton;
    }

    private SenatorSingleton(Context context){mContext = context.getApplicationContext();
        mDatabase = new SenatorDatabase(mContext).getWritableDatabase();

    }

    private SenatorDatabaseWrapper querySenator(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(SenatorTable.NAME,null,whereClause,whereArgs,null,null,null);
        return new SenatorDatabaseWrapper(cursor);

    }

    List<Senator> allSenators() {

        List<Senator> senatorsList = new ArrayList<>();
        SenatorDatabaseWrapper cursor = querySenator(null,null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                senatorsList.add(cursor.getSenator());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
     return senatorsList;
    }



    /*public Cursor searchSenator(String text){

        String search ="SELECT * FROM " +SenatorTable.NAME+" WHERE "+ SenatorTable.Cols.NAME +" LIKE  '%" + text + "%'";
        Cursor cursor = mDatabase.rawQuery(search,null);

        if (cursor.getCount() == 0) {
            return null;
        }

        return cursor;

    }*/

    public List<Senator> getSenator(String text) {
    SenatorDatabaseWrapper cursor = querySenator(SenatorTable.Cols.NAME + " like ?", new String[] { '%'+ text + '%' });
    List<Senator> searchedList = new ArrayList<>();
    Senator senate;

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                senate = cursor.getSenator();
                searchedList.add(senate);
                cursor.moveToNext();
            }

            return searchedList;
        } finally {
            cursor.close();
        }

    }


    public Senator getSenatorId(UUID id) {
        SenatorDatabaseWrapper cursor = querySenator(SenatorTable.Cols.UUID + " = ?", new String[] { id.toString() });
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getSenatorById();
        } finally {
            cursor.close();
        }
    }
}
