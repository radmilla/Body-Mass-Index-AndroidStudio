package com.example.body_mass_index;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     EditText height, weight;
     TextView result;
     Button calculate,  clearData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height = (EditText) findViewById((R.id.height));
        weight = (EditText) findViewById((R.id.weight));
        result = (TextView) findViewById((R.id.result));
        calculate = (Button) findViewById((R.id.calculate));
        clearData = (Button) findViewById(R.id.clearData);


        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBMI();
            }
        });



        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void clearBMI() {
        height.setText("");
        weight.setText("");
        result.setText("");
    }


    private void calculateBMI() {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr.equals("")){
            height.setError("Please Enter Your Height");
            height.requestFocus();
            return;
        }
        if (weightStr.equals("")){
            weight.setError("Please Enter Your Weight");
            weight.requestFocus();
            return;
        }

            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);

    }

    private void displayBMI (Float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <=0) {
            bmiLabel = "Severely underweight";
        } else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <=0){
            bmiLabel = "Underweight";
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <=0){
            bmiLabel = "Normal";
        }else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <=0){
            bmiLabel = "Overweight";
        } else {
            bmiLabel = "Obese";
        }
            bmiLabel = "Your Body Mass Index is: " + "\n" + bmi + "\n" + "It's " + bmiLabel;
        result.setText(bmiLabel);


    }

}
