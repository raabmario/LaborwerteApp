package com.example.mario.laborwerteapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LaborwerteApp.db";

    //Struktur Users Tabelle
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_UID = "_uid";
    public static final String COLUMN_USERFIRSTNAME = "userfirstname";
    public static final String COLUMN_USERLASTNAME = "userlastname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DOB = "DOB";
    public static final String COLUMN_GEN = "GEN";

    //Struktur Eintrag Tabelle
    private static final String TABLE_EINTRAG = "eintrag";
    private static final String COLUMN_EID = "_eid";
    public static final String COLUMN_EUID = "_euid";
    public static final String COLUMN_EBID = "_ebid";
    public static final String COLUMN_WERT = "wert";
    public static final String COLUMN_DATUM = "datum";

    //Struktur Blutwertkategorie Tabelle

    private static final String TABLE_BLUTWERTKATEGORIE = "blutwertkategorie";
    private static final String COLUMN_BID = "_bid";
    public static final String COLUMN_ABK = "abk";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "_escription";
    public static final String COLUMN_AGEGROUP = "agegroup";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_NORMRANGELOW = "normrangelow";
    public static final String COLUMN_NORMRANGEHIGH = "normrangehigh";
    public static final String COLUMN_UNIT = "unit";



    //Konstruktor
    public MyDBHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Erstellen von Users Tabelle
        String query1 = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERFIRSTNAME + " TEXT, " +
                COLUMN_USERLASTNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_DOB + " TEXT, " +
                COLUMN_GEN + " TEXT" +
                ");";
        db.execSQL(query1);

        //Erstelen von Eintrag Tabelle
        String query2 = "CREATE TABLE "+TABLE_EINTRAG+"("+
                COLUMN_EID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_EUID + " TEXT, " +
                COLUMN_EBID+ " TEXT, "+
                COLUMN_WERT+ " TEXT, " +
                COLUMN_DATUM + " TEXT" +
                ");";
        db.execSQL(query2);

        //Erstellen von Blutwertkategorie Tabelle
        String query3 = "CREATE TABLE " + TABLE_BLUTWERTKATEGORIE + "(" +
                COLUMN_BID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ABK + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_AGEGROUP + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_NORMRANGELOW + " DOUBLE, " +
                COLUMN_NORMRANGEHIGH + " DOUBLE, " +
                COLUMN_UNIT + " TEXT" +
                ");";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EINTRAG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BLUTWERTKATEGORIE);
        onCreate(db);
    }

    //Methoden für TABLE_USERS

    public void addUser(Users user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERFIRSTNAME, user.get_usernamefirst());
        values.put(COLUMN_USERLASTNAME, user.get_usernamelast());
        values.put(COLUMN_EMAIL, user.get_email());
        values.put(COLUMN_DOB, user.get_DOB());
        values.put(COLUMN_GEN, user.get_GEN());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }


    public void editUser(int userID, String VornameNeu, String NachnameNeu, String EmailNeu, String DOBNeu, String GENNeu) {
        SQLiteDatabase db = getWritableDatabase();
        String query1 = "UPDATE " + TABLE_USERS + " SET " + COLUMN_USERFIRSTNAME + " = '" + VornameNeu + "' WHERE " + COLUMN_UID + " ='" + userID + "'";
        String query2 = "UPDATE " + TABLE_USERS + " SET " + COLUMN_USERLASTNAME + " = '" + NachnameNeu + "' WHERE " + COLUMN_UID + " ='" + userID + "'";
        String query3 = "UPDATE " + TABLE_USERS + " SET " + COLUMN_EMAIL + " = '" + EmailNeu + "' WHERE " + COLUMN_UID + " ='" + userID + "'";
        String query4 = "UPDATE " + TABLE_USERS + " SET " + COLUMN_DOB + " = '" + DOBNeu + "' WHERE " + COLUMN_UID + " ='" + userID + "'";
        String query5 = "UPDATE " + TABLE_USERS + " SET " + COLUMN_GEN + " = '" + GENNeu + "' WHERE " + COLUMN_UID + " ='" + userID + "'";

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);

        db.close();
    }

    public Cursor getAllDataUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        return c;
    }

    public Cursor getUID_relatedData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ TABLE_USERS + " WHERE "+COLUMN_UID+" = "+id, null);
        return c;
    }

    public void clearTableUsers(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, null, null);
    }

    //Methoden für TABLE_EINTRAG

    public void addEintrag(Eintrag eintrag){
        ContentValues values = new ContentValues();
        values.put(COLUMN_EUID, eintrag.get_userID());
        values.put(COLUMN_EBID, eintrag.get_blutID());
        values.put(COLUMN_WERT, eintrag.get_blutWert());
        values.put(COLUMN_DATUM, eintrag.get_datum());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EINTRAG, null, values);
        db.close();
    }

    public Cursor getAllDataEintrag() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_EINTRAG, null);
        return c;
    }

    public Cursor findBloodIDbyName(String name){
        //Funktion von Meli um mit Blutkategorie die ID zu erhalten  (Suchanfrage um Alter und Geschlecht erweitern)
        SQLiteDatabase db = this.getWritableDatabase();
        //String x = "SELECT " + COLUMN_BID + " FROM " + TABLE_BLUTWERTKATEGORIE + " WHERE " + COLUMN_NAME + " = ?";
        Cursor d = db.rawQuery("SELECT " + COLUMN_BID + " FROM " + TABLE_BLUTWERTKATEGORIE + " WHERE " + COLUMN_NAME + " = ?", new String[] {name});
        return d;
    }

    public void clearTableEintrag(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_EINTRAG, null, null);
    }

    public Cursor findApplicableBlutwertkategoriebyUID(int UID){
        //Daten von UserID bekommen
        Cursor c = getUID_relatedData(UID);

        if(c.getCount() == 0) {

            c = null;
        }
        StringBuffer gen_buffer = new StringBuffer();
        StringBuffer dob_buffer = new StringBuffer();
        while (c.moveToNext()) {

            dob_buffer.append(c.getString(4));
            gen_buffer.append(c.getString(5));
        }

        String gender = gen_buffer.toString();
        String stringUserDOB = dob_buffer.toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //String stringUserDOB = c.getString(4);
        Date userDOB = null;
        try{
            userDOB = sdf.parse(stringUserDOB);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar DOB = toCalendar(userDOB);
        Calendar today = Calendar.getInstance();

        int age = ageCalc(today, DOB);
        String ageGroup = "";

        if(age >= 18) {ageGroup = "Erwachsen";}
        else{ageGroup = "Kind";}

        //Database Query nach AGEGROUP und GENDER

        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor d = db.rawQuery("SELECT " +COLUMN_ABK+ " FROM "+TABLE_BLUTWERTKATEGORIE+" WHERE ("+COLUMN_AGEGROUP+" = '"+ageGroup+"' AND "+ COLUMN_GENDER+" = '"+gender+"')", null);
        Cursor d = db.rawQuery("SELECT * FROM "+TABLE_BLUTWERTKATEGORIE+" WHERE ("+COLUMN_AGEGROUP+" = '"+ageGroup+"' AND "+ COLUMN_GENDER+" = '"+gender+"')", null);

        return d;
    }

    public Cursor findApplicableDataEintrag(int UID, String BID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_EINTRAG+" WHERE ("+COLUMN_EUID+" = '"+UID+"' AND "+ COLUMN_EBID+" = '"+BID+"')", null);

        return c;
    }

    public Cursor findEintragbyEID(String [] ids){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlID_array = TextUtils.join(",", ids);
        //Cursor c = db.rawQuery("SELECT * FROM " + TABLE_EINTRAG + " WHERE " + COLUMN_EID + " = ?",ids);
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_EINTRAG+ " WHERE "+COLUMN_EID+" IN ("+sqlID_array+")",null);
        return c;
    }


    //Hilfsmethoden
    public static Calendar toCalendar (Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static int ageCalc(Calendar Date1, Calendar Date2){
        int years = 0;
        int months = 0;
        int days = 0;

        //Get difference between years
        years = Date1.get(Calendar.YEAR) - Date2.get(Calendar.YEAR);
        int currMonth = Date1.get(Calendar.MONTH) + 1;
        int birthMonth = Date2.get(Calendar.MONTH) + 1;

        //Get difference between months
        months = currMonth - birthMonth;

        //if month difference is in negative then reduce years by one
        //and calculate the number of months.
        if (months < 0)
        {
            years--;
            months = 12 - birthMonth + currMonth;
            if (Date1.get(Calendar.DATE) < Date2.get(Calendar.DATE))
                months--;
        } else if (months == 0 && Date1.get(Calendar.DATE) < Date2.get(Calendar.DATE))
        {
            years--;
            months = 11;
        }

        //Calculate the days
        if (Date1.get(Calendar.DATE) > Date2.get(Calendar.DATE))
            days = Date1.get(Calendar.DATE) - Date2.get(Calendar.DATE);
        else if (Date1.get(Calendar.DATE) < Date2.get(Calendar.DATE))
        {
            int today = Date1.get(Calendar.DAY_OF_MONTH);
            Date1.add(Calendar.MONTH, -1);
            days = Date1.getActualMaximum(Calendar.DAY_OF_MONTH) - Date2.get(Calendar.DAY_OF_MONTH) + today;
        }
        else
        {
            days = 0;
            if (months == 12)
            {
                years++;
                months = 0;
            }
        }


        return years;
    }



    //Methoden für TABLE_BLUTWERTKATEGORIE

    public void addBlutwertkategorie(Blutwertkategorie blutwertkategorie){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ABK, blutwertkategorie.getAbk());
        values.put(COLUMN_NAME, blutwertkategorie.getName());
        values.put(COLUMN_DESCRIPTION, blutwertkategorie.getDescription());
        values.put(COLUMN_AGEGROUP, blutwertkategorie.getAgeGroup());
        values.put(COLUMN_GENDER, blutwertkategorie.getGender());
        values.put(COLUMN_NORMRANGELOW, blutwertkategorie.getNormRangeLow());
        values.put(COLUMN_NORMRANGEHIGH, blutwertkategorie.getNormRangeHigh());
        values.put(COLUMN_UNIT, blutwertkategorie.getUnit());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_BLUTWERTKATEGORIE, null, values);
        db.close();
    }

    public Cursor getAllDataBlutwertkategorie() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_BLUTWERTKATEGORIE, null);
        return c;
    }

    public void clearTableBlutwertkategorie(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, null, null);
    }


}
