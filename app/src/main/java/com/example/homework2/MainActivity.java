package com.example.homework2;

/**
 * @author Erik Liu
 * September 2019
 *
 * the MainActivity.java sets up all of the buttons used in the GUI for the listeners in the face
 * class
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Face guiFaceView = (Face) findViewById(R.id.faceview);

        //sets up the slide bars
        SeekBar redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        redSeekBar.setOnSeekBarChangeListener(guiFaceView);

        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        greenSeekBar.setOnSeekBarChangeListener(guiFaceView);

        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        blueSeekBar.setOnSeekBarChangeListener(guiFaceView);

        //sets up the buttons to choose what colors we are changing
        RadioGroup HESGroup = (RadioGroup) findViewById(R.id.hairEyeSkin);
        HESGroup.setOnCheckedChangeListener(guiFaceView);

        //sets up the drop down menu for the hair styles
        /**
         * external citation
         * Date: 28 September 2019
         * Problem: Could not get the drop down menu values set up
         * Resource: https://www.mkyong.com/android/android-spinner-drop-down-list-example/
         * Solution: I used parts of the example code to fill out what I didn't have
         */
        String[] hairStyles = {"Bowl Cut", "Short", "Long"};
        Spinner hairChoice = (Spinner) findViewById(R.id.hairSpinner);
        hairChoice.setOnItemSelectedListener(guiFaceView);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hairStyles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairChoice.setAdapter(dataAdapter);

        //sets up the randomize button
        Button random = (Button) findViewById(R.id.randomizeButton);
        random.setOnClickListener(guiFaceView);


    }

}
