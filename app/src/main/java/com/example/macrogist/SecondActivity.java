package com.example.macrogist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText ageEditText, heightEditText, weightEditText;
    private RadioGroup genderRadioGroup;
    private Spinner activitySpinner;
    private CheckBox loseWeightCheckbox, gainMuscleCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ageEditText = findViewById(R.id.ageEditText);
        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        activitySpinner = findViewById(R.id.activitySpinner);
        loseWeightCheckbox = findViewById(R.id.loseWeightCheckbox);
        gainMuscleCheckbox = findViewById(R.id.gainMuscleCheckbox);

        Button calButton = findViewById(R.id.calculateButton);
        Button clearButton = findViewById(R.id.clearButton);

        String[] activityLevels = new String[]{"Not Active", "Active", "Very Active"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, activityLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(adapter);

        calButton.setOnClickListener(v -> {
            if (isInputValid()) {
                int age = Integer.parseInt(ageEditText.getText().toString());
                float height = Float.parseFloat(heightEditText.getText().toString());
                float weight = Float.parseFloat(weightEditText.getText().toString());

                String gender = "Not Selected";
                int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
                if (selectedGenderId == R.id.radioMale) {
                    gender = "Male";
                } else if (selectedGenderId == R.id.radioFemale) {
                    gender = "Female";
                }

                String activityLevel = activitySpinner.getSelectedItem().toString();

                boolean wantsToLoseWeight = loseWeightCheckbox.isChecked();
                boolean wantsToGainMuscle = gainMuscleCheckbox.isChecked();

                Person person = new Person(age, height, weight, gender, activityLevel, wantsToLoseWeight, wantsToGainMuscle);

                String macrosResult = person.calculateMacros();

                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("macroResult", macrosResult);
                startActivity(intent);
            } else {
                Toast.makeText(SecondActivity.this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(v -> clearFields());
    }

    private boolean isInputValid() {
        if (ageEditText.getText().toString().isEmpty() ||
                heightEditText.getText().toString().isEmpty() ||
                weightEditText.getText().toString().isEmpty()) {
            return false;
        }

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {  // No gender selected
            return false;
        }


        if (!loseWeightCheckbox.isChecked() && !gainMuscleCheckbox.isChecked()) {
            return false;
        }

        return true;
    }

    private void clearFields() {
        ageEditText.setText("");
        heightEditText.setText("");
        weightEditText.setText("");
        genderRadioGroup.clearCheck();
        activitySpinner.setSelection(0);
        loseWeightCheckbox.setChecked(false);
        gainMuscleCheckbox.setChecked(false);
    }
}
