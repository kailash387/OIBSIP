package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnitSpinner, toUnitSpinner;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);


        String[] units = {"Meter", "Kilometer", "Centimeter", "Millimeter"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String input = inputValue.getText().toString();
        if (input.isEmpty()) {
            resultTextView.setText("Please enter a value");
            return;
        }

        double value = Double.parseDouble(input);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double result = convert(value, fromUnit, toUnit);
        resultTextView.setText(String.valueOf(result));
    }
    private double convert(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return value;
        }

        // Direct conversions
        if (fromUnit.equals("Kilometer") && toUnit.equals("Centimeter")) {
            return value * 100000;
        }
        if (fromUnit.equals("Kilometer") && toUnit.equals("Millimeter")) {
            return value * 1000000;
        }
        if (fromUnit.equals("Kilometer") && toUnit.equals("Meter")) {
            return value * 1000;
        }
        if (fromUnit.equals("Centimeter") && toUnit.equals("Kilometer")) {
            return value / 100000;
        }
        if (fromUnit.equals("Centimeter") && toUnit.equals("Millimeter")) {
            return value * 10;
        }
        if (fromUnit.equals("Centimeter") && toUnit.equals("Meter")) {
            return value / 100;
        }
        if (fromUnit.equals("Millimeter") && toUnit.equals("Kilometer")) {
            return value / 1000000;
        }
        if (fromUnit.equals("Millimeter") && toUnit.equals("Centimeter")) {
            return value / 10;
        }
        if (fromUnit.equals("Millimeter") && toUnit.equals("Meter")) {
            return value / 1000;
        }
        if (fromUnit.equals("Meter") && toUnit.equals("Kilometer")) {
            return value / 1000;
        }
        if (fromUnit.equals("Meter") && toUnit.equals("Centimeter")) {
            return value * 100;
        }
        if (fromUnit.equals("Meter") && toUnit.equals("Millimeter")) {
            return value * 1000;
        }

        return value;
    }

}
