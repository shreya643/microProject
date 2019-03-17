package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(this);
        databaseOpenHelper.drop();
        databaseOpenHelper.convertShow();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.big);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent= new Intent(MainActivity.this, HallActivity.class);
                intent.putExtra("message", "Big Movies");
                startActivity(intent);
            }});

    }
}