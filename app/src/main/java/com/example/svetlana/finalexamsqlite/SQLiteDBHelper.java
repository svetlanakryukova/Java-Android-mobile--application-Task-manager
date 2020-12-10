package com.example.svetlana.finalexamsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "task_title";
    private static final String COLUMN_PRIORITY = "task_priority";


    SQLiteDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_PRIORITY + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //read all Tasks from our DB

    Cursor readAllTasks(){
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+COLUMN_PRIORITY+" DESC, "+COLUMN_TITLE;
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //Delete all task from DB
    void deleteAllTasks(){
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    //save one task in our DB
    void saveTask(String title, String priority){

        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_PRIORITY, priority);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "New Task Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //delete one task from DB
    void deleteOneRow(String row_id){
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Task as accomplished. Deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
