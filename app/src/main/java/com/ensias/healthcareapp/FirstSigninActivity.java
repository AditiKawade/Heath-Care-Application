package com.ensias.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ensias.healthcareapp.fireStoreApi.UserHelper;

public class FirstSigninActivity extends AppCompatActivity {
    private EditText fullName ;
    private EditText birthday ;
    private EditText teL ;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_signin);
        btn =(Button) findViewById(R.id.confirmeBtn);
        fullName = (EditText)findViewById(R.id.firstSignFullName);
        birthday = (EditText)findViewById(R.id.firstSignBirthDay);
        teL = (EditText)findViewById(R.id.firstSignTel);



        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname,birtDay,tel,type;
                fullname=fullName.getText().toString();
                birtDay=birthday.getText().toString();
                tel=teL.getText().toString();
                type=spinner.getSelectedItem().toString();
                UserHelper.addUser(fullname,birtDay,tel,type);
                Intent k = new Intent(FirstSigninActivity.this, MainActivity.class);
                startActivity(k);
            }
        });
    }
}
