package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstNumber, etSecondNumber;
    private Spinner spinnerOperation;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstNumber = findViewById(R.id.etFirstNumber);
        etSecondNumber = findViewById(R.id.etSecondNumber);
        spinnerOperation = findViewById(R.id.spinnerOperation);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        // Define the operations directly in Java
        String[] operations = {"Addition", "Subtraction", "Multiplication", "Division"};

        // Set up the Spinner with the operations array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperation.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        String num1Str = etFirstNumber.getText().toString();
        String num2Str = etSecondNumber.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        String operation = spinnerOperation.getSelectedItem().toString();
        double result = 0;

        switch (operation) {
            case "Addition":
                result = num1 + num2;
                break;
            case "Subtraction":
                result = num1 - num2;
                break;
            case "Multiplication":
                result = num1 * num2;
                break;
            case "Division":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    Toast.makeText(this, "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }

        tvResult.setText("Result: " + result);
    }
}
