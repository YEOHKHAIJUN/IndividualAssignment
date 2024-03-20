package com.example.individualassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Game3 extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, button6, backBtn;
    TextView text1, text2, text3, text4, text5;
    TextView titleTV, totalRoundTV;
    int totalround = 1;
    int targetNumber;

    int buttonsChosen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        button1.setOnClickListener(buttonClickListener);
        button2.setOnClickListener(buttonClickListener);
        button3.setOnClickListener(buttonClickListener);
        button4.setOnClickListener(buttonClickListener);
        button5.setOnClickListener(buttonClickListener);
        button6.setOnClickListener(buttonClickListener);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        totalRoundTV = findViewById(R.id.totalRound);

        backBtn = findViewById(R.id.backBtn); // Initialize the back button
        titleTV = findViewById(R.id.title); // Initialize the title TextView


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Main = new Intent(Game3.this, MainActivity.class);
                startActivity(intent_Main);
            }
        });
        generateNumbers();
        playGame();
    }

    private void playGame() {
//            generateNumbers();
    }

    private boolean checkButtonsRemaining() {
        // Check if any of the buttons are visible (not hidden)
        return button1.getVisibility() == View.VISIBLE ||
                button2.getVisibility() == View.VISIBLE ||
                button3.getVisibility() == View.VISIBLE ||
                button4.getVisibility() == View.VISIBLE ||
                button5.getVisibility() == View.VISIBLE ||
                button6.getVisibility() == View.VISIBLE;
    }

    private void resetGame() {
        text1.setText("");
        text3.setText("");

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);

    }

    private void generateNumbers() {
        Random rand = new Random();
        resetGame();

        // Generate random numbers between 0 and 99 for each button
        int ans = rand.nextInt(100) + 50;
        int num1 = rand.nextInt(100);
        while (num1 > ans){
            num1 = rand.nextInt(100);
        }
        int num2 = rand.nextInt(100);
        while (num2 > ans){
            num2 = rand.nextInt(100);
        }
        int num3 = rand.nextInt(100);
        while (num3 > ans){
            num3 = rand.nextInt(100);
        }
        int num4 = ans - num1;
        int num5 = ans - num2;
        int num6 = ans - num3;

        button1.setText(String.valueOf(num1));
        button2.setText(String.valueOf(num2));
        button3.setText(String.valueOf(num3));
        button4.setText(String.valueOf(num4));
        button5.setText(String.valueOf(num5));
        button6.setText(String.valueOf(num6));

        text5.setText(String.valueOf(ans));
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof Button) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                if (text1.getText().toString().isEmpty()) {
                    text1.setText(buttonText);
                } else if (text3.getText().toString().isEmpty()) {
                    text3.setText(buttonText);
                }

                button.setVisibility(View.GONE);

                boolean number1Filled = !(text1.getText().toString().isEmpty());
                boolean number3Filled = !(text3.getText().toString().isEmpty());

                if (number1Filled && number3Filled) {
                    if (checkAnswer()) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                        totalround++;
                        totalRoundTV.setText("Round " + String.valueOf(totalround));

                        if (totalround < 5) {
                            resetGame();
                            generateNumbers();
                        } else {
                            showResult();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong! Try Again", Toast.LENGTH_SHORT).show();
                        resetGame();
                    }
                }
                }
        }
    };

    private boolean checkAnswer() {
        // Get the numbers from text1 and text3
        int number1 = Integer.parseInt(text1.getText().toString());
        int number3 = Integer.parseInt(text3.getText().toString());
        int number5 = Integer.parseInt(text5.getText().toString());

        // Calculate the sum of text1 and text3
        int sum = number1 + number3;

        // Perform your check here, for example:
        if (sum == number5) {
            // Correct answer
            return true;
        } else {
            // Incorrect answer
            return false;
        }
    }


    private void showResult() {
        // Display the final result or perform any other actions

        titleTV.setText(" Congratulation! \n You have completed this game.");
        text1.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);
        text4.setVisibility(View.GONE);
        text5.setVisibility(View.GONE);
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        button5.setVisibility(View.GONE);
        button6.setVisibility(View.GONE);


        totalRoundTV.setVisibility(View.GONE);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleTV.getLayoutParams();
        params.topMargin = 400; // Set the top margin here
        titleTV.setLayoutParams(params);
        ViewGroup.MarginLayoutParams params1 = (ViewGroup.MarginLayoutParams) backBtn.getLayoutParams();
        params1.bottomMargin = 400; // Set the top margin here
        backBtn.setLayoutParams(params);
    }

}