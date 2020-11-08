package com.example.markotesic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


public class FeedReederDbHelper extends SQLiteOpenHelper {

    public FeedReederDbHelper(Context context){
        super(context,"Marko.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Korisnik " +
                 "(idkorisnik integer PRIMARY KEY AUTOINCREMENT, ime TEXT NOT NULL, prezime TEXT NOT NULL, korisnickoIme TEXT NOT NULL, sifra TEXT NOT NULL);");
         sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS  DNEVNIK "+
                 "(iddnevnik integer PRIMARY KEY AUTOINCREMENT," +
                 " datum DATE NOT NULL," +
                 " komentar TEXT NOT NULL, " +
                 " terapija TEXT NOT NULL," +
                 "idkorisnik integer NOT NULL,"+
                 "FOREIGN KEY (idkorisnik) REFERENCES Korisnik(idkorisnik) ON DELETE CASCADE ON UPDATE NO ACTION); ");

    }
    //CREATE TABLE User()
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Cursor getKorisnik(String korisnickoIme, String sifra){
        Cursor c=getReadableDatabase().rawQuery("Select * FROM Korisnik k Where k.korisnickoIme='"+korisnickoIme+"' and k.sifra='"+sifra+"'",null);
        return c;
    }
    public boolean saveKorisnik(String korisnickoime, String sifra, String ime, String prezime){
        ContentValues contentValues=new ContentValues();
        contentValues.put("ime",ime);
        contentValues.put("prezime",prezime);
        contentValues.put("korisnickoIme",korisnickoime);
        contentValues.put("sifra",sifra);
        getWritableDatabase().insert("Korisnik",null,contentValues);
        return true;
    }
    public Cursor  getDnevnikByKorisnik(int idKorisnik){
         Cursor cursor= getReadableDatabase().rawQuery("SELECT * FROM Dnevnik d WHERE d.idkorisnik='"+idKorisnik+"'",null);
         return cursor;
    }
    public void insertDnevnik(int idKorisnik,String komentar,String terapija){
        ContentValues contentValues=new ContentValues();
        contentValues.put("datum",new Date().toString());
        contentValues.put("komentar",komentar);
        contentValues.put("terapija",terapija);
        contentValues.put("idkorisnik",idKorisnik);
        getWritableDatabase().insert("Dnevnik",null,contentValues);
    }
    public Cursor getAll(){
        return getReadableDatabase().rawQuery("SELECT * FROM Dnevnik k",null);
    }

}
