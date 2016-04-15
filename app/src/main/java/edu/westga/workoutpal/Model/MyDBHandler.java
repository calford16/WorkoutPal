package edu.westga.workoutpal.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Cory on 4/14/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "statsDB.db";
    public static final String TABLE_STATS = "stats";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MUSCLE = "musclegroup";
    public static final String COLUMN_DATE = "date";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void addStat(String muscle) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_MUSCLE, muscle);
        values.put(COLUMN_DATE, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insert(TABLE_STATS, null, values);
            db.close();
        } catch (Exception e) {
            return;
        } finally {
            if (db != null)
                db.close();
        }
    }

    public ArrayList<String> getStats() {
        String query = "Select * FROM " + TABLE_STATS + " WHERE " + COLUMN_DATE + " BETWEEN datetime('now', 'start of month') AND datetime('now', 'localtime');";
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery(query, null);

            ArrayList<String> stats = new ArrayList<String>();

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                stats.add(cursor.getString(1));
                cursor.moveToNext();
            }
            return stats;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null)
                db.close();
        }
    }

    public boolean deleteStat(String muscle) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_STATS + " WHERE " + COLUMN_MUSCLE + "= '" + muscle +
                "' ORDER by " + COLUMN_DATE + " DESC" +
                " LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery(query, null);
            System.out.println(cursor.getCount());
            if (cursor.moveToFirst()) {
                db.delete(TABLE_STATS, COLUMN_ID + " = ?", new String[]{cursor.getString(0)});
                cursor.close();
                result = true;
            }
            db.close();
            return result;
        } catch (Exception e) {
            return false;
        } finally {
            if (db != null)
                db.close();
        }
    }

    public int clearStats() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            int count = db.delete(TABLE_STATS, "1", null);
            db.close();
            return count;
        } catch (Exception e) {
            return 0;
        } finally {
            if (db != null)
                db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STATS_TABLE = "CREATE TABLE " + TABLE_STATS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_MUSCLE
                + " TEXT," + COLUMN_DATE + " DATE)";
        db.execSQL(CREATE_STATS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        onCreate(db);
    }
}
