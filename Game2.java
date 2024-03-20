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

public class Game2 extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, backBtn;
    TextView text1, text2, text3, text4, text5;
    TextView titleTV, totalRoundTV;
    int totalround = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        button1.setOnClickListener(buttonClickListener);
        button2.setOnClickListener(buttonClickListener);
        button3.setOnClickListener(buttonClickListener);
        button4.setOnClickListener(buttonClickListener);
        button5.setOnClickListener(buttonClickListener);

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
                Intent intent_Main = new Intent(Game2.this, MainActivity.class);
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
                button5.getVisibility() == View.VISIBLE;
    }

    private void resetGame() {
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);

    }

    private void generateNumbers() {
        Random rand = new Random();
        resetGame();

        button1.setText(String.valueOf(rand.nextInt(100))); // Generate random numbers between 0 and 99
        button2.setText(String.valueOf(rand.nextInt(100))); // Generate random numbers between 0 and 99
        button3.setText(String.valueOf(rand.nextInt(100))); // Generate random numbers between 0 and 99
        button4.setText(String.valueOf(rand.nextInt(100))); // Generate random numbers between 0 and 99
        button5.setText(String.valueOf(rand.nextInt(100))); // Generate random numbers between 0 and 99

        titleTV.setText(new Random().nextInt(2) == 0 ? "Ascending Order" : "Descending Order");
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof Button) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                if (text1.getText().toString().isEmpty()) {
                    text1.setText(buttonText);
                } else if (text2.getText().toString().isEmpty()) {
                    text2.setText(buttonText);
                } else if (text3.getText().toString().isEmpty()) {
                    text3.setText(buttonText);
                } else if (text4.getText().toString().isEmpty()) {
                    text4.setText(buttonText);
                } else if (text5.getText().toString().isEmpty()) {
                    text5.setText(buttonText);
                }
                button.setVisibility(View.GONE); // To hide the button completely

                if (!checkButtonsRemaining()) {
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
        int[] numbers = new int[5];

        // Read values from text1 to text5 and store them in the array
        for (int i = 1; i <= 5; i++) {
            int resourceId = getResources().getIdentifier("text" + i, "id", getPackageName());
            TextView textView = findViewById(resourceId);
            String text = textView.getText().toString();
            if (text.isEmpty()) {
                // Handle the case when any of the TextViews is empty
                return false;
            }
            numbers[i - 1] = Integer.parseInt(text);
        }

        // Check if the array is sorted
        for (int i = 0; i < numbers.length - 1; i++) {
            if (titleTV.getText().toString().equals("Ascending Order")) {
                // Check for ascending order
                if (numbers[i] > numbers[i + 1]) {
                    // The array is not sorted in ascending order
                    return false;
                }
            } else {
                // Check for descending order
                if (numbers[i] < numbers[i + 1]) {
                    // The array is not sorted in descending order
                    return false;
                }
            }
        }
        return true;
    }

    private void showResult() {
        // Display the final result or perform any other actions

        titleTV.setText("Congratulation! \n Well done!");
        text1.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);
        text4.setVisibility(View.GONE);
        text5.setVisibility(View.GONE);
        totalRoundTV.setVisibility(View.GONE);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleTV.getLayoutParams();
        params.topMargin = 400; // Set the top margin here
        titleTV.setLayoutParams(params);

    }

}