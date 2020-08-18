package com.example.appchallenge1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText edtTxtName, edtTxtEmail, edtTxtPassward, editTxtRePassward;
    private Button regButton, imageButton;
    private TextView txtAgreement, txtGender, txtCountry, txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnRepassword;
    private Spinner spinner;
    private CheckBox checkbox;
    private RadioGroup rgGender;
    private RelativeLayout recView1, recView2, recView3, parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Yet to learn", Toast.LENGTH_SHORT).show();
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: started");

        if (validateData()) {
            if (checkbox.isChecked()) {
                showSnackBar();
            } else {
                Toast.makeText(this, "You need to agree to the licence agreement ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: started");

        txtWarnName.setVisibility(View.INVISIBLE);
        txtWarnEmail.setVisibility(View.INVISIBLE);
        txtWarnPassword.setVisibility(View.INVISIBLE);
        txtWarnRepassword.setVisibility(View.INVISIBLE);

        String name = edtTxtName.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String country = spinner.getSelectedItem().toString();
        String gender = "";

        switch(rgGender.getCheckedRadioButtonId()){
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                gender = "Unknown";
                break;
        }
        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: "+ gender + "\n" +
                "Country: "+ country ;
        Log.d(TAG, "showSnackBar: Snack Bar Text : "+ snackText);
        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtTxtName.setText("");
                        edtTxtEmail.setText("");
                        edtTxtPassward.setText("");
                        editTxtRePassward.setText("");
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: started");
        if (edtTxtName.getText().toString().equals("")) {
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter your name");
            return false;
        }
        if (edtTxtEmail.getText().toString().equals("")) {
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Enter your Email");
            return false;
        }
        if (edtTxtPassward.getText().toString().equals("")) {
            txtWarnPassword.setVisibility(View.VISIBLE);
            txtWarnPassword.setText("Enter your Password");
            return false;
        }
        if (editTxtRePassward.getText().toString().equals("")) {
            txtWarnRepassword.setVisibility(View.VISIBLE);
            txtWarnRepassword.setText("Re Enter your Password");
            return false;
        }

        if (edtTxtPassward.getText().toString().length() < 6 || editTxtRePassward.getText().toString().length() < 6) {
            Toast.makeText(this, "Password should be atleast 6 charcters long", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (editTxtRePassward.getText().toString().equals(edtTxtPassward.getText().toString()) == false) {
            Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private void initViews() {
        Log.d(TAG, "initViews: started");
        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassward = findViewById(R.id.edtTxtPassword);
        editTxtRePassward = findViewById(R.id.edtTxtRePassword);

        imageButton = findViewById(R.id.imageButton);
        regButton = findViewById(R.id.regButton);

        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnPassword = findViewById(R.id.txtWarnPassword);
        txtWarnRepassword = findViewById(R.id.txtWarnRepassword);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);

        txtGender = findViewById(R.id.txtGender);
        txtCountry = findViewById(R.id.txtCountry);

        spinner = findViewById(R.id.spinner);
        checkbox = findViewById(R.id.checkbox);
        rgGender = findViewById(R.id.rgGender);
        parent = findViewById(R.id.parent);

    }
}