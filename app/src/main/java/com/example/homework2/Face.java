package com.example.homework2;

/**
 * @author Erik Liu
 * September 2019
 *
 * Face.java
 *
 * This class holds all of the variables used to draw the face, the listeners for all of the
 * functions on the GUI, and the methods needed to draw on the surface view.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import java.util.Random;

public class Face extends SurfaceView implements android.widget.SeekBar.OnSeekBarChangeListener,
        RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, View.OnClickListener{

    //variables needed for the creation of the face
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;

    public int red, green, blue;

    public int radioSelect = SKIN;//used to keep track of what 'mode' we are in

    //static variables used for the radioSelect
    public static final int HAIR = 0;
    public static final int EYE = 1;
    public static final int SKIN = 2;

    //static variables used for the hairStyles
    public static final int BOWL = 0;
    public static final int SHORT = 1;
    public static final int LONG = 2;

    Paint myPaint = new Paint();

    public Face(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);
        randomize();//randomize the first face
        setBackgroundColor(Color.WHITE);
    }

    public void randomize() {
        //using a random number, sets all of the variables in the Face class
        Random r = new Random();
        hairStyle = r.nextInt(3);
        eyeColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        hairColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        skinColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }


    //methods for drawing on the surface view
    public void onDraw(Canvas canvas) {
        //methods used to draw out the face
        //draw the head
        myPaint.setColor(skinColor);
        canvas.drawOval(250, 200, 950, 1200, myPaint);

        //draw a mouth
        myPaint.setColor(Color.BLACK);
        canvas.drawArc(450, 800, 750, 1000, 180, -180, false, myPaint);

        //rest of the methods to draw the face
        drawEyes(canvas);
        drawHair(canvas);
    }

    public void drawEyes(Canvas canvas){
        //draw three concentric circles for the eyes
        myPaint.setColor(Color.BLACK);
        canvas.drawOval(400, 375, 550,525, myPaint);
        canvas.drawOval(650, 375, 800, 525, myPaint);

        myPaint.setColor(Color.WHITE);
        canvas.drawOval(425, 400, 525,500, myPaint);
        canvas.drawOval(675, 400, 775, 500, myPaint);

        myPaint.setColor(eyeColor);
        canvas.drawOval(450, 425, 500, 475, myPaint);
        canvas.drawOval(700, 425, 750, 475, myPaint);
    }

    public void drawHair(Canvas canvas){
        //based on what hairStyle and hairColor, draw the hair with these given variables
        myPaint.setColor(hairColor);
        switch(hairStyle){
            case BOWL:
                canvas.drawArc(200, 150, 1000, 700, 180, 180, false, myPaint);
                break;
            case LONG:
                canvas.drawRect(200, 150, 400, 1000, myPaint);
                canvas.drawRect(800, 150, 1000, 1000, myPaint);
            case SHORT:
                canvas.drawRect(200, 150, 1000, 425, myPaint);
                break;
        }
        invalidate();
    }


    //methods for the listeners for all the buttons on the GUI

    //methods for SeekBar
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
       //gets the colors from the slide bar
        switch(seekBar.getId()){
            case R.id.redSeekBar:
                red = progress;
                break;
            case R.id.greenSeekBar:
                green = progress;
                break;
            case R.id.blueSeekBar:
                blue = progress;
                break;
        }

        //sets the color to the appropriate variable
        if(radioSelect == SKIN)
            skinColor = Color.rgb(red, green, blue);
        else if(radioSelect == EYE)
            eyeColor = Color.rgb(red, green, blue);
        else if(radioSelect == HAIR)
            hairColor = Color.rgb(red, green, blue);

        invalidate();
    }


    public void onStartTrackingTouch(SeekBar seekBar){}
    public void onStopTrackingTouch(SeekBar seekBar){}

    //methods for radioGroup
    public void onCheckedChanged(RadioGroup group, int checkedId){
        //figures out what button we are currently looking at for the slide bars
        switch(checkedId){
            case R.id.hairButton:
                radioSelect = HAIR;
                break;
            case R.id.eyeButton:
                radioSelect = EYE;
                break;
            case R.id.skinButton:
                radioSelect = SKIN;
                break;
        }
        invalidate();
    }

    //methods for spinner
    //figures out what hairStyle was picked from the drop down menu
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        hairStyle = position;
    }

    public void onNothingSelected(AdapterView<?> parent){}

    //methods for button
    //randomizes the entire face
    public void onClick(View v){
        randomize();
    }

}
