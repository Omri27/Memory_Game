package com.example.omri.memory_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class Level_1_Activity extends AppCompatActivity {
    private Chronometer chronometer;
    private TextView user_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1);
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.USER_NAME);
        this.user_Name =(TextView)findViewById(R.id.userText);
        this.user_Name.setText(name);
        this.chronometer=(Chronometer)findViewById(R.id.chronometer);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        new GameLogic(2, getImages(table),this.chronometer,this);
    }

    private ArrayList<ImageView> getImages(TableLayout layout) {


        ArrayList<ImageView> images = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount(); i++) {
            TableRow row = (TableRow) layout.getChildAt(i);

            for (int j = 0; j < row.getChildCount(); j++) {

                View subView = row.getChildAt(j);

                if (subView instanceof ImageView) {
                    ImageView imageView = (ImageView) subView;
                    images.add(imageView);
                    //manipulate the imageView
                }
            }

        }
        return images;
    }
}