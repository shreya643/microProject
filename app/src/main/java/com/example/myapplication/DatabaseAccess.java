package com.example.myapplication;


import android.content.Context;
import android.util.Log;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    public void convertShow(Context context) {
        DatabaseOpenHelper databaseOpenHelper=new DatabaseOpenHelper(context);
        if(!Python.isStarted()) {
            Python.start(Python.getPlatform());
        }
        Python py = Python.getInstance();
        PyObject pym=py.getModule("big");
        List<PyObject> list=pym.callAttr("main").asList();
        for(PyObject showTime:list)
        {
            ShowTiming show=new ShowTiming();
            show.setMovie(showTime.get("movie").toString());
            show.setTimings(showTime.get("timming").toString());
            show.setImage(showTime.get("url").toString());
            show.setCategory(showTime.get("category").toString());
            show.setMovieLength(showTime.get("length").toString());
            show.setHall(showTime.get("hall").toString());

            databaseOpenHelper.addHandler(show);
        }

    }
}
