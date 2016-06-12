package com.myfavoriteplaces.myfavoriteplaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BDManager {
    private static final String TABLE_NAME = "places";
    public static final String KEY_ID_PLACE = "_id";
    public static final String KEY_NOM_PLACE = "nom_place";
    public static final String KEY_TYPE_PLACE = "type_place";
    public static final String KEY_ADDRESS_PLACE = "address_place";
    public static final String KEY_DESCRIPTION_PLACE = "description_place";
    public static final String CREATE_TABLE_PLACES = "CREATE TABLE "
            + TABLE_NAME + "("
            + KEY_ID_PLACE + " INTEGER PRIMARY KEY, "
            + KEY_NOM_PLACE +" TEXT, "
            + KEY_TYPE_PLACE +" TEXT, "
            + KEY_ADDRESS_PLACE +" TEXT, "
            + KEY_DESCRIPTION_PLACE +" TEXT);";

    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;

    public BDManager(Context context){
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open() {
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long addPlace(BD place){
        ContentValues values = new ContentValues();
        values.put(KEY_NOM_PLACE, place.getNom_place());
        values.put(KEY_TYPE_PLACE, place.getType_place());
        values.put(KEY_ADDRESS_PLACE, place.getAddress_place());
        values.put(KEY_DESCRIPTION_PLACE, place.getDescription_place());

        return db.insert(TABLE_NAME,null,values);
    }

    public int modPlace(String old_name, BD place){
        ContentValues values = new ContentValues();
        values.put(KEY_NOM_PLACE, place.getNom_place());
        values.put(KEY_TYPE_PLACE, place.getType_place());
        values.put(KEY_ADDRESS_PLACE, place.getAddress_place());
        values.put(KEY_DESCRIPTION_PLACE, place.getDescription_place());

        String where = KEY_NOM_PLACE+" = ?";
        String[] whereArgs = {old_name+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int suppPlace(String place){

        String where = KEY_NOM_PLACE+" = ?";
        String[] whereArgs = {place+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public BD getPlace(String place){
        BD p = new BD("","","","");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_NOM_PLACE+"='"+place+"'", null);
        if (c.moveToFirst()){
            p.setId_place(c.getInt(c.getColumnIndex(KEY_ID_PLACE)));
            p.setNom_place(c.getString(c.getColumnIndex(KEY_NOM_PLACE)));
            p.setType_place(c.getString(c.getColumnIndex(KEY_TYPE_PLACE)));
            p.setAddress_place(c.getString(c.getColumnIndex(KEY_ADDRESS_PLACE)));
            p.setDescription_place(c.getString(c.getColumnIndex(KEY_DESCRIPTION_PLACE)));

            c.close();
        }

        return p;
    }

    public Cursor getPlaces(){
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
}
