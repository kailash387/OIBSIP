package com.example.quizapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;

public class MainActivity extends AppCompatActivity {

    TextView questionTextView;
    RadioGroup optionsGroup;
    RadioButton option1, option2, option3, option4;
    Button startQuizButton, submitButton;
    int currentQuestionIndex = 0;
    int score = 0;

    String[] questions = {
            "What is the capital of France?",
            "What is 2 + 2?",
            "Who wrote 'Romeo and Juliet'?"
    };

    String[][] options = {
            {"Paris", "London", "Berlin", "Madrid"},
            {"3", "4", "5", "6"},
            {"Shakespeare", "Hemingway", "Tolkien", "Austen"}
    };

    String[] correctAnswers = {"Paris", "4", "Shakespeare"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        questionTextView = findViewById(R.id.questionTextView);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        startQuizButton = findViewById(R.id.startQuizButton);
        submitButton = findViewById(R.id.submitButton);

        // Initially hide the quiz views
        questionTextView.setVisibility(View.GONE);
        optionsGroup.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);

        // Start Quiz Button click listener
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizButton.setVisibility(View.GONE);
                loadQuestion();
                questionTextView.setVisibility(View.VISIBLE);
                optionsGroup.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.VISIBLE);
            }
        });

        // Submit Button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionTextView.setText(questions[currentQuestionIndex]);
            option1.setText(options[currentQuestionIndex][0]);
            option2.setText(options[currentQuestionIndex][1]);
            option3.setText(options[currentQuestionIndex][2]);
            option4.setText(options[currentQuestionIndex][3]);
        } else {
            Toast.makeText(this, "Quiz Finished! Your score is: " + score, Toast.LENGTH_LONG).show();
            resetQuiz();
        }
    }

    private void checkAnswer() {
        int selectedOptionId = optionsGroup.getCheckedRadioButtonId();
        RadioButton selectedOption = findViewById(selectedOptionId);
        if (selectedOption != null && selectedOption.getText().equals(correctAnswers[currentQuestionIndex])) {
            score++;
        }
        currentQuestionIndex++;
        optionsGroup.clearCheck();
        loadQuestion();
    }

    private void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        startQuizButton.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.GONE);
        optionsGroup.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
    }
}
