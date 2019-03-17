package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HallActivity extends AppCompatActivity {
    public GridLayout gridLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hall);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        final DatabaseOpenHelper databaseOpenHelper=new DatabaseOpenHelper(this);
        FloatingActionButton b=(FloatingActionButton) findViewById(R.id.refresh);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                databaseOpenHelper.drop();
                databaseOpenHelper.convertShow();
            }});

        List <ShowTiming> movieTime= databaseOpenHelper.loadHandler();

    for (ShowTiming show : movieTime) {

            if (show.getHall().equals(message)) {
                this.gridLayout = findViewById(R.id.tab_big);
                this.gridLayout.setColumnCount(2);
                this.gridLayout.setColumnOrderPreserved(true);
                this.gridLayout.setUseDefaultMargins(true);
                this.gridLayout.setRowOrderPreserved(true);
                TableLayout tableLayout=new TableLayout(this);
                tableLayout.setPadding(10,2,10,20);
                TableRow row1 = new TableRow(this);
                TableRow row2 = new TableRow(this);
                TableRow row3 = new TableRow(this);
                TableRow row4 = new TableRow(this);
                row1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
                row2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
                row3.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
                row4.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));

                TextView movie = new TextView(this);
                movie.setText(show.getMovie());
                movie.setWidth(150);
                movie.setTextSize(26);
                movie.setTextScaleX(1f);
                TextView category = new TextView(this);
                category.setText("Category: " + show.getCategory());
                TextView length = new TextView(this);
                length.setText("Movie Length: " + show.getMovieLength() + " min");

                String timings[]=show.getTimings().split("-");
                GridLayout gridLayout=new GridLayout(this);
                gridLayout.setColumnCount(3);
                for(String time:timings) {
                    Button button=new Button(this);
                    button.setText(time);
                    gridLayout.addView(button);
                }
                row4.addView(gridLayout);
                ImageView image=new ImageView(this);
                Glide.with( this)
                        .load(show.getImage())
                        .apply(new RequestOptions()
                                .override(380,420)
                        )
                        .into(image);

                row1.addView(movie);
                row2.addView(length);
                row3.addView(category);

                tableLayout.addView(row1);
                tableLayout.addView(row2);
                tableLayout.addView(row3);
                tableLayout.addView(row4);
                tableLayout.setMinimumWidth(200);

                this.gridLayout.addView(tableLayout);
                this.gridLayout.addView(image);
            }


//        }
        }
    }
}
