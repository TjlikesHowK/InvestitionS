package com.example.investitions.adapters

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOGO + " TEXT,"
                + NAME + " TEXT,"
                + TICKER + " TEXT)")
        db.execSQL(query)
    }
    fun addNew(
        logo: String?,
        name: String?,
        ticker: String?
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(LOGO, logo)
        values.put(NAME, name)
        values.put(TICKER, ticker)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun read(): ArrayList<SearchStockData> {
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        val courseModalArrayList: ArrayList<SearchStockData> = ArrayList()

        if (cursor.moveToFirst()) {
            do {
                courseModalArrayList.add(
                    SearchStockData(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()

        return courseModalArrayList
    }

    fun delete(stock: String) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "name=?", arrayOf(stock))
        db.close()
    }

    companion object {
        private const val DB_NAME = "stocksdb"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "stocks"
        private const val ID_COL = "id"
        private const val LOGO = "logo"
        private const val NAME = "name"
        private const val TICKER = "ticker"
    }
}