package com.example.omri.memory_game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    public final static String USER_NAME = "com.example.Memory_Game.USERNAME";
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Intent intent;
    private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameLabel= (TextView)findViewById(R.id.name_Label);
        nameLabel.setText("User Name:");

        this.sharedPref = this.getSharedPreferences("allUsers", Context.MODE_PRIVATE);
        this.editor = sharedPref.edit();
        final EditText username = (EditText) findViewById(R.id.username);
        listener    =   new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (username.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please Enter a Username!", Toast.LENGTH_SHORT).show();
                } else
                    switch (v.getId()) {
                        case R.id.level1:
                            intent = new Intent(MainActivity.this, Level_1_Activity.class);
                            intent.putExtra(USER_NAME, username.getText().toString());
                            startActivity(intent);
                            break;
                        case R.id.level2:
                            intent = new Intent(MainActivity.this, Level_2_Activity.class);
                            intent.putExtra(USER_NAME, username.getText().toString());
                            startActivity(intent);
                            break;
                        case R.id.level3:
                            intent = new Intent(MainActivity.this, Level_3_Activity.class);
                            intent.putExtra(USER_NAME, username.getText().toString());
                            startActivity(intent);
                            break;
                    }
            }
        };
        Button Level1=(Button)findViewById(R.id.level1);
        Button Level2=(Button)findViewById(R.id.level2);
        Button Level3=(Button)findViewById(R.id.level3);
        Level1.setOnClickListener(listener);
        Level2.setOnClickListener(listener);
        Level3.setOnClickListener(listener);

    }

}