package com.example.individualassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Game1 extends AppCompatActivity {
    private TextView num1TV, num2TV, resultTV, TV1;
    private Button greaterBtn, lessBtn, equalBtn, backBtn;

    private int[] num1 = new int[5]; // Array to store first set of numbers
    private int[] num2 = new int[5]; // Array to store second set of numbers
    private int comIndex = 0; // Index to keep track of comparison
    private int count = 0; // While the answer is correct, it will count 1 to show the result


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        num1TV = findViewById(R.id.num1TV);
        num2TV = findViewById(R.id.num2TV);
        resultTV = findViewById(R.id.resultTV);
        greaterBtn = findViewById(R.id.greaterBtn);
        lessBtn = findViewById(R.id.lessBtn);
        equalBtn = findViewById(R.id.equalBtn);
        backBtn = findViewById(R.id.backBtn);
        TV1 = findViewById(R.id.TV1);

        TV1.setText("Compare number from left to right");


        generateNumbers();

        greaterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult("greater");
            }
        });

        lessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult("less");
            }
        });

        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult("equal");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Main = new Intent(Game1.this,MainActivity.class);
                startActivity(intent_Main);
            }
        });
    }

    private void generateNumbers() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            num1[i] = rand.nextInt(100); // Generate random numbers between 0 and 99
            num2[i] = rand.nextInt(100);
        }
        showNextComparison();
    }

    private void showNextComparison() {
        if (comIndex < 5) {
            num1TV.setText(String.valueOf(num1[comIndex]));
            num2TV.setText(String.valueOf(num2[comIndex]));
        }
    }


    private void checkResult(String option) {
        if (comIndex < 5) {
            int n1 = num1[comIndex];
            int n2 = num2[comIndex];
            comIndex++;
            if (option.equals("greater")) {
                if (n1 > n2) {
                    count++;
                    resultTV.setText( "Correct!\n" + count + " / " +comIndex);
                }
                else
                    resultTV.setText("Wrong!\n " + count + " / "+comIndex);
            } else if (option.equals("less")) {
                if (n1 < n2) {
                    count++;
                    resultTV.setText( "Correct!\n" + count + " / "+comIndex);
                }
                else
                    resultTV.setText("Wrong!\n " + count + " / "+comIndex);
            } else if (option.equals("equal")) {
                if (n1 == n2){
                    count++;
                    resultTV.setText( "Correct!\n" + count + " / "+comIndex);
                }
                else
                    resultTV.setText("Wrong!\n " + count + " / "+comIndex);
            }
            showNextComparison();
        }else {
            showResult();
        }

    }

    // Generate new numbers for the next comparison
    private void showResult() {
        // Display the final result or perform any other actions
        num1TV.setVisibility(View.GONE);
        num2TV.setVisibility(View.GONE);
        TV1.setVisibility(View.GONE);
        greaterBtn.setVisibility(View.GONE);
        lessBtn.setVisibility(View.GONE);
        equalBtn.setVisibility(View.GONE);

        resultTV.setText("All comparisons done!\n\nResult: " + count + " / " + comIndex);


        // Optionally, reset any state for future use
        comIndex = 0;

    }
}