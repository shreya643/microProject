package com.popcorn.time;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(this);
        databaseOpenHelper.drop();
        databaseOpenHelper.convertShow("main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void buttonOnClick(View view)
    {
        Intent intent= null;
        switch(view.getId())
        {
            case R.id.big:
                intent= new Intent(MainActivity.this, HallActivity.class);
                intent.putExtra("module", "bigMovies");
                intent.putExtra("hallName","Big Movies");
                startActivity(intent);
                break;

            case R.id.kumari:
                intent= new Intent(MainActivity.this, HallActivity.class);
                intent.putExtra("module", "qfx");
                intent.putExtra("hallName","QFX Kumari");
                startActivity(intent);
                break;

            case R.id.chayaCentre:
                intent= new Intent(MainActivity.this, HallActivity.class);
                intent.putExtra("module", "qfx");
                intent.putExtra("hallName","QFX Chhaya Center");
                startActivity(intent);
                break;

            case R.id.civilMall:
                intent= new Intent(MainActivity.this, HallActivity.class);
                intent.putExtra("module", "qfx");
                intent.putExtra("hallName","QFX Civil Mall");
                startActivity(intent);
                break;

//            case R.id.chayaCentre:
//                // Code for button 3 click
//                break;
        }
    }
}