package com.example.andriod.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button [][] buttons = new Button[3][3];
    private TextView textPlayer1 ;
    private TextView textPlayer2 ;
    private boolean play1Turn = true ;
    private int numberOfRound = 0;
    private int play_1_Ponts;
    private int play_2_Ponts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get
        textPlayer1 = findViewById(R.id.text_p1);
        //get
        textPlayer2 = findViewById(R.id.text_p2);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String resourceId = "button_"+i+j;
                int resID = getResources().getIdentifier(resourceId,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button reset = findViewById(R.id.reset_button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")) {
            return;
        }
        if(play1Turn){
            ((Button)v).setText("X");
        }else {
            ((Button)v).setText("O");
        }
        numberOfRound++;
        if(checkForWiner()){
            if(play1Turn){
                play1Wins();
            }else {
                play2Wins();
            }
        }else if(numberOfRound >=9){
                draw();
        }else {
            play1Turn = !play1Turn;
        }

    }

    private boolean checkForWiner(){
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;

    }

    private void play1Wins() {
        play_1_Ponts++;
        Toast.makeText(this,"Player 1 win",Toast.LENGTH_SHORT).show();
        updateScoreText();
        restBoard();
    }
    private void play2Wins() {
        play_2_Ponts++;
        Toast.makeText(this,"Player 2 win",Toast.LENGTH_SHORT).show();
        updateScoreText();
        restBoard();
    }
    private void draw() {
        Toast.makeText(this,"draw",Toast.LENGTH_SHORT).show();
        restBoard();
    }
    private void updateScoreText(){
        textPlayer1.setText("Player 1 : " + play_1_Ponts);
        textPlayer2.setText("Player 1 : " + play_2_Ponts);
    }
    private void restBoard(){
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        numberOfRound = 0;
        play1Turn = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("player1points",play_1_Ponts);
        outState.putInt("player2points",play_2_Ponts);
        outState.putInt("numberofround",numberOfRound);
        outState.putBoolean("player1Tern",play1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        play1Turn = savedInstanceState.getBoolean("player1Tern");
        play_1_Ponts = savedInstanceState.getInt("play_1_Ponts");
        play_2_Ponts = savedInstanceState.getInt("play_2_Ponts");
        numberOfRound = savedInstanceState.getInt("numberofround");
    }
}
