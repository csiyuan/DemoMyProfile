package sg.edu.rpc346.id19016011.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.rGroup);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int intGenderId = rgGender.getCheckedRadioButtonId();
                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.putInt("genderId", intGenderId);
                Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //step 2a:
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //step 2b:
        String strName = prefs.getString("name", "Name");
        float gpa = prefs.getFloat("gpa", 0.0f);
        int gender = prefs.getInt("genderID", R.id.radioButtonGenderMale);
        //Step 2c:
        etName.setText(strName);
        etGPA.setText(gpa+"");
        rgGender.check(gender);

    }

    @Override
    protected void onPause() {
        super.onPause();

        save();
    }

    protected void save(){
        //step 1a:
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int intGenderID = rgGender.getCheckedRadioButtonId();
        //step 1b:
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //step 1c:
        SharedPreferences.Editor prefEdit = prefs.edit();
        //step 1d:
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putInt("genderID",intGenderID);
        //step 1e:
        prefEdit.commit();
    }

    }



