package com.kdhf.keuanganku.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kdhf.keuanganku.model.Uang;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_keuangan"; // NAMA DATABASE
    private static final String TABLE_NAME = "table_keuangan"; // NAMA TABEL
    private static final String COLUMN_ID = "id"; // NAMA KOLOM ID
    private static final String COLUMN_PEMASUKAN = "income"; // NAMA KOLOM PEMASUKAN
    private static final String COLUMN_PENGELUARAN = "outcome"; // NAMA KOLOM PENGELUARAN

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Fungsi untuk membuat databasenya
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PEMASUKAN + " TEXT,"
                + COLUMN_PENGELUARAN + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }
    //Fungsi untuk mengecek databasenya ada atau tidak
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //Fungsi untuk menambah data mahasiswa
    public void tambahUang(Uang uang){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PEMASUKAN, uang.getPemasukan());
        values.put(COLUMN_PENGELUARAN, uang.getPengeluaran());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    //Fungsi untuk mengambil 1 data mahasiswa
    public Uang getUang(int id_uang){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_PEMASUKAN, COLUMN_PENGELUARAN},
                COLUMN_ID + "=?", new String[]{String.valueOf(id_uang)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Uang uang = new Uang(cursor.getString(1), cursor.getString(2));
        return uang;
    }
    //Fungsi untuk mengambil semua data mahasiswa
    public List<Uang> getSemuaUang(){
        List<Uang> uangList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Uang uang = new Uang(cursor.getString(1), cursor.getString(2));
                uangList.add(uang);
            } while (cursor.moveToNext());
        }
        return uangList;
    }
    //Fungsi untuk menghitung ada beberapa data
    public int getUangCount(){
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
    //Fungsi update data Mahasiswa
    public int updateDataMahasiswa(Uang uang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PEMASUKAN, uang.getPemasukan());
        values.put(COLUMN_PENGELUARAN, uang.getPengeluaran());
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(uang.getId())});
    }
    //Fungsi menghapus data 1 mahasiswa
    public void hapusDataMahasiswa(Uang uang) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(uang.getId())});
        db.close();
    }
    //Fungsi menghapus semua data mahasiswa
    public void hapusSemuaDataMahasiswa(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
