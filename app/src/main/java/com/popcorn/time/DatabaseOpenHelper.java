package com.popcorn.time;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.util.ArrayList;
import java.util.List;


public class DatabaseOpenHelper extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test.db";
    public static final String TABLE_NAME = "Movies";
    //initialize the database
    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE MOVIES (ID INTEGER PRIMARY KEY  AUTOINCREMENT , Movie CHAR(100) NOT NULL, Category CHAR(100) NOT NULL,Movie_Length CHAR(50),Timings CHAR(50) NOT NULL, Hall TEXT NOT NULL, Image CHAR(100) NOT NULL)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public void addHandler(ShowTiming showTiming) {
        ContentValues values = new ContentValues();
        values.put("Movie", showTiming.getMovie());
        values.put("Category", showTiming.getCategory());
        values.put("Movie_Length",showTiming.getMovieLength());
        values.put("Timings",showTiming.getTimings());
        values.put("Hall",showTiming.getHall());
        values.put("Image",showTiming.getImage());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();

        return;
    }

    public List<ShowTiming> loadHandler() {
        List<ShowTiming> list = new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MOVIES", null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            try {
                ShowTiming show = new ShowTiming();
                show.setId(cursor.getInt(0));
                show.setMovie(cursor.getString(1));
                show.setCategory(cursor.getString(2));
                show.setMovieLength(cursor.getString(3));
                show.setTimings(cursor.getString(4));
                show.setHall(cursor.getString(5));
                show.setImage(cursor.getString(6));

                Log.d("msg2",(cursor.getString(6)));

                // Adding person to list
                list.add(show);
            }
            catch(Exception ex){

                Log.d("msg1","hi");
                ex.printStackTrace();
            }
        }

        cursor.close();
        Log.d("msg1","hiii");
        return list;
    }

    public void drop()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM  MOVIES");
        db.close();
    }


    public void convertShow(String message) {
        if(!Python.isStarted()) {
            Python.start(Python.getPlatform());
        }
        Python py = Python.getInstance();
        PyObject pym=py.getModule("scrapeHalls");
        Log.d("msg5",message);
        List<PyObject> list=pym.callAttr(message).asList();
        for(PyObject showTime:list)
        {
            ShowTiming show=new ShowTiming();
            show.setMovie(showTime.get("movie").toString());
            show.setTimings(showTime.get("timming").toString());
            show.setImage(showTime.get("url").toString());
            show.setCategory(showTime.get("category").toString());
            show.setMovieLength(showTime.get("length").toString());
            show.setHall(showTime.get("hall").toString());

            addHandler(show);
        }

    }

}