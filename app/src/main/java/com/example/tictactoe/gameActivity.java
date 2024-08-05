package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class gameActivity extends AppCompatActivity {

    // initialize class
    private SoundPlayer sound;


    boolean gameActive = true;
    // Player Representation :
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meaning:,
    // 0 - X
    // 1 - O
    // 2 - Null

    int [][] winPostitions = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};
    public static int counter;

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage]==2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            counter++;
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                sound.playXSound();
                activePlayer = 1;
                TextView status = findViewById(R.id.statusbar);
                status.setText("O's turn - Tap to play");
            }
            else {
                img.setImageResource(R.drawable.o);
                sound.playOSound();
                activePlayer = 0;
                TextView status = findViewById(R.id.statusbar);
                status.setText("X's turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        int flag = 0;
        int i=0; //this counter is to know which winPosition we get or where to place line

        for(int[] winPositions: winPostitions){
            Log.d("jarvis", "inside for loop- first log: " +
                    "                           \ngameState[winPositions[0]]: "+gameState[winPositions[0]] +
                                                "\ngameState[winPositions[1]]: "+gameState[winPositions[1]]
                                                +"\ngameState[winPositions[2]]: "+gameState[winPositions[2]]+"\n"+100*'-');
            i++;
            if (gameState[winPositions[0]] == gameState[winPositions[1]] &&
                    gameState[winPositions[1]] == gameState[winPositions[2]] &&
                    gameState[winPositions[0]] != 2){
                Log.d("jarvis", "winposition is :"+i);

                //Somebody has won - Find out who?
                String winStr;
                flag = 1;
                gameActive = false;
                if(gameState[winPositions[0]] == 0){
                    Log.d("jarvis", "playerTap(inside if statement in for loop): gameState[winPositions[0]]: "+ gameState[winPositions[0]]);
                    winStr = "X Won - ";
                }
                else{
                    Log.d("jarvis", "playerTap(inside else): gameStae[winPositions[0]]: " + gameState[winPositions[0]]);
                    winStr = "O Won - ";
                }
                // to know how the user is won and where to place line
                String lineStr=setLines(i);
                // Update the status bar for winner announcement
                TextView finalMsg = findViewById(R.id.finalMsg);
                TextView status = findViewById(R.id.statusbar);
                status.setText(winStr+lineStr);
                sound.playWinSound();
                finalMsg.setTextColor(Color.RED);
                finalMsg.setText("Game Stop!!! " + winStr);
            }

        }
        if (counter==9 && flag==0) {
            // take care : if the screen is rotated then everything resets, but the counter doesn't reset. It will continue from where it was.
            // so try to lock the screen rotation. -- locked
            TextView status = findViewById(R.id.statusbar);
            TextView finalMsg = findViewById(R.id.finalMsg);
            sound.playDeuseSound();
            status.setText("Draw");
            finalMsg.setText("It's a Draw!!");
            finalMsg.setTextColor(Color.BLACK);
            counter=0;
        }

    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for( int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        ((ImageView)findViewById(R.id.setLine)).setVisibility(View.INVISIBLE); // making line image invisible

        TextView status = findViewById(R.id.statusbar);
        status.setText("X's turn - Tap to play");
        status.setTextColor(Color.GRAY);
        TextView finalMsg = findViewById(R.id.finalMsg);
        finalMsg.setText("Play Play Play !!!");
        finalMsg.setTextColor(Color.BLUE);
        finalMsg.setAlpha(0.5f);
        counter = 0;
    }

    public String setLines(int winType){
        String winTypeStr="";
        ImageView setLine= (ImageView) findViewById(R.id.setLine);
        if (winType==1) {
            setLine.setTranslationX(-1000f);
            setLine.setImageResource(R.drawable.line01);
            setLine.setVisibility(View.VISIBLE);
            winTypeStr="first row horizontally";
            setLine.animate().translationXBy(1000f).setDuration(300);
        }
        if (winType==2){
            setLine.setTranslationX(-1000f);
            setLine.setVisibility(View.VISIBLE);
            setLine.setImageResource(R.drawable.line02);
            winTypeStr="second row horizontally";
            setLine.animate().translationXBy(1000f).setDuration(300);
        }
        if (winType==3){
            setLine.setTranslationX(-1000f);
            setLine.setVisibility(View.VISIBLE);
            setLine.setImageResource(R.drawable.line03);
            winTypeStr="third row horizontally";
            setLine.animate().translationXBy(1000f).setDuration(300);
        }
        if (winType==4){
            setLine.setTranslationY(-1000f);
            setLine.setVisibility(View.VISIBLE);
            setLine.setImageResource(R.drawable.line04);
            winTypeStr="first column vertically";
            setLine.animate().translationYBy(1000f).setDuration(300);
        }
        if (winType==5){
            setLine.setTranslationY(-1000f);
            setLine.setVisibility(View.VISIBLE);
            setLine.setImageResource(R.drawable.line05);
            winTypeStr="second column vertically";
            setLine.animate().translationYBy(1000f).setDuration(300);
        }
        if (winType==6){
            setLine.setTranslationY(-1000f);
            setLine.setVisibility(View.VISIBLE);
            setLine.setImageResource(R.drawable.line06);
            winTypeStr="third column vertically";
            setLine.animate().translationYBy(1000f).setDuration(300);
        }
        if (winType==7){
            setLine.setTranslationX(-1000f);
            setLine.setVisibility(View.VISIBLE);
            setLine.setImageResource(R.drawable.line07);
            winTypeStr="diagonally from left to right";
            setLine.animate().translationXBy(1000f).setDuration(300);
        }
        if (winType==8){
            setLine.setTranslationX(1000f);
            setLine.setVisibility(View.VISIBLE);
            setLine.setImageResource(R.drawable.line08);
            winTypeStr="diagonally from right to left";
            setLine.animate().translationXBy(-1000f).setDuration(300);
        }

        return winTypeStr;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sound = new SoundPlayer(this);
        // To remove status bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SoundPlayer.soundFlag==1){
                    SoundPlayer.soundFlag = 0;
                    imageButton.setImageResource(R.drawable.sound_off);
                }
                else{
                    SoundPlayer.soundFlag = 1;
                    imageButton.setImageResource(R.drawable.sound_on);
                }

            }
        });

    }

}

