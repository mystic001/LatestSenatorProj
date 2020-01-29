package com.example.latestsenatorproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.latestsenatorproj.SenatorDbSchema.*;

import androidx.annotation.Nullable;

public class SenatorDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;


    public SenatorDatabase(@Nullable Context context) {

        super(context, SenatorTable.NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + SenatorTable.NAME + "("+ SenatorTable.Cols.COL1
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SenatorTable.Cols.UUID+" " +
                "," +SenatorTable.Cols.NAME +" ," +SenatorTable.Cols.EMAIL+", "
                +SenatorTable.Cols.PHONE +" , "+ SenatorTable.Cols.STATE  + ")" );
                addMember(db);

    }

    public void onOpen(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + SenatorTable.NAME);
        onCreate(db);
    }

    private void addMember(SQLiteDatabase db){

        Senator[] senatorsList = {
                new Senator (1,"Sen. E. Abaribe","enyiabaribe@yahoo.com ", "08033129452 ", "ABIA"),
                new Senator (2,"Sen. O. Kalu ", "okalu@orjikalu.com", "08034000001","ABIA"),
                new Senator (3,"Sen. T. Orji","senatortaorji@gmail.com","07082800000","ABIA"),
                new Senator (4,"Sen. B. Yaroe ","Xbdyaroe@gmail.com","08034050460","ADAMAWA"),
                new Senator (5,"Sen. I. Abbo" , "faradugun@gmail.com" ,"08066285112","ADAMAWA"),
                new Senator (6,"Sen. A. Ahmed", "aishatu.ahmed@nass.gov.ng", "Not Available","ADAMAWA"),
                new Senator (7,"Sen. B. Akpan","akpanalbert@hotmail.com","08055555188","AKWA IBOM"),
                new Senator (8,",Sen. A. Eyakenyi"," konssie@yahoo.com","08035054282","AKWA IBOM") ,
                new Senator (9,"Sen. C. Ekpeyong","chrisekpesg@yahoo.com","08027785234","AKWA IBOM"),
                new Senator (10," Sen. I. Ubah ","senatorifeanyiubah@gmail.com","09096655596  ","ANAMBRA"),
                new Senator (11,",Sen. E. Uche","u.ekwunife@yahoo.com","08037620002 ","ANAMBRA"),
                new Senator (12," Sen. A. Oduah ","senatorstella@gmail.com","08055084340","ANAMBRA"),
                new Senator (13,"Sen. H. Jika","jikahalliru@gmail.com"," 08038666690 ","ANAMBRA"),
                new Senator (14,"Sen. A. Bulkachuwa","adamu.bulkachuwa@nass.gov.ng","Not Available","ANAMBRA")
                /*new Senator (15,"","","",""),
                new Senator (16,"","","",""),
                new Senator (17,"","","",""),
                new Senator (18,"","","",""),
                new Senator (19,"","","",""),
                new Senator (20,"","","",""),
                new Senator (21,"","","",""),
                new Senator (22,"","","",""),
                new Senator (23,"","","",""),
                new Senator (24,"","","",""),
                new Senator (25,"","","",""),
                new Senator (26,"","","",""),
                new Senator (27,"","","",""),*/

        };

        for (Senator senator : senatorsList) {
            addSenator(db, senator);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private void addSenator(SQLiteDatabase db, Senator senator){
        ContentValues values = new ContentValues();
        values.put(SenatorTable.Cols.UUID,senator.getUd().toString());
        values.put(SenatorTable.Cols.NAME,senator.getName());
        values.put(SenatorTable.Cols.EMAIL,senator.getEmail());
        values.put(SenatorTable.Cols.PHONE,senator.getPhone());
        values.put(SenatorTable.Cols.STATE,senator.getState());
        db.insert(SenatorTable.NAME,null,values);
    }
}
