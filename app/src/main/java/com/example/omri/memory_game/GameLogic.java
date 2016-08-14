package com.example.omri.memory_game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameLogic {
    private Chronometer chronometer;
    private int isFlipped = -1;
    private ArrayList<ImageView> pictures;
    private Integer[] squares;
    private int counter;
    private Activity currentActivity;
    private int numOfImages;
    private int[] imagesIds = {

            R.drawable.paragliding1,
            R.drawable.paragliding2,
            R.drawable.paragliding3,
            R.drawable.paragliding4,
            R.drawable.paragliding5,
            R.drawable.paragliding6,


    };

    public GameLogic(int numOfImages, ArrayList<ImageView> pictures,Chronometer chron,Activity activity) {
        this.numOfImages = numOfImages;
        this.chronometer = chron;
        this.currentActivity=activity;
        this.pictures=pictures;
        this.chronometer.start();
        List iSquares = new ArrayList();
        for (int i = 0; i < this.numOfImages; i++) {
            iSquares.add(i);
            iSquares.add(i);
        }
        Collections.shuffle(iSquares);
        this.squares = (Integer[]) iSquares.toArray(new Integer[this.numOfImages]);
        for (int i = 0; i < this.numOfImages * 2; i++)
            AdjustClicks(i);
    }

    private void AdjustClicks(final int cell) {
        ImageView picture = this.pictures.get(cell);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(cell);
                if (isFlipped == -1 || isFlipped == cell) {
                    isFlipped = cell;
                } else if (squares[isFlipped] == squares[cell]) {
                    removeListener(cell);
                    removeListener(isFlipped);
                    counter++;
                    if (counter == numOfImages) {
                        chronometer.stop();
                        new AlertDialog.Builder(currentActivity)
                                .setTitle("Finished The Level!")
                                .setMessage("Time: " + Double.toString((double) (SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000) + " Seconds")
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        currentActivity.finish();

                                    }
                                }).show();
                    }
                    isFlipped = -1;
                } else
                    makeUnavailable(cell);



            }


        });

    }
    private void makeUnavailable(final int cell){
        unSetClickable();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                unflip(cell);
                unflip(isFlipped);
                setClickable();
                isFlipped = -1;
            }
        }, 500);
    }
    private void unSetClickable() {

        for (ImageView image : this.pictures) {
            image.setClickable(false);

        }
    }

    private void setClickable() {
        for (ImageView image : this.pictures) {
            image.setClickable(true);
        }
    }

    private void show(int index) {
        ImageView image = this.pictures.get(index);
        image.setImageResource(this.imagesIds[this.squares[index]]);

    }

    private void removeListener(int index) {
        ImageView image = this.pictures.get(index);
        image.setOnClickListener(null);
    }

    private void unflip(int index) {
        ImageView image = this.pictures.get(index);

        image.setImageResource(R.drawable.gray);

    }
}