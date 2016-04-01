package com.phd.lucas.morales.clemencio.caronte;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.phd.lucas.morales.clemencio.caronte.domain.Email;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    public static final int MAXIMUM_AGE = 100;
    public static final int MINIMUM_AGE = 10;
    public static final int FIRST_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupRegisterButton();
        retrieveEmailAndPassword();
        populateDropdowns();
    }

    //TODO 1: Programar lógica (a prueba de errores) del boton Register (comprobar los campos y la lógica- contraseñas coincidentes etc y registrarse)
        /*
            A) Quitar toda referencia a thortful en las clases Email, EmailConstants y EmailTest
            B) Ver por qué no funcionan los tests y averiguar como meter JUnit
            C) Seguir programando la logica del boton Register (abajo, en esta misma clase)
         */
    //TODO 2: Pensar que hacer con el RegisterActivity (cuadradito azul) que aparece en esta pantalla al hacer scroll hacia abajo
    private void populateDropdowns(){
        populateAgeDropdown();
        populateEthnicGroupDropdown();
        populateMaritalStatus();
        populateEducationLevel();
        populateWorkingSituation();
        populateSalaryPerYear();
        populateDisabilityLevel();
    }

    private void populateAgeDropdown() {
        String [] items = new String[MAXIMUM_AGE - MINIMUM_AGE];
        for(int i = MINIMUM_AGE; i < MAXIMUM_AGE; i++){
            items[i - MINIMUM_AGE] = String.valueOf(i);
        }
        List<String> list = Arrays.asList(items);
        list = new ArrayList<>(list);
        list.add(FIRST_POSITION, getResources().getString(R.string.select_age)); // Initial dummy entry as hint
        populateSpinner(list, (Spinner) findViewById(R.id.spinnerAge));
    }

    private void populateEthnicGroupDropdown() {
        String [] items = getResources().getStringArray(R.array.ethnic_origin);
        List<String> list = Arrays.asList(items);
        list = new ArrayList<>(list);
        list.add(FIRST_POSITION, getResources().getString(R.string.select_ethnic_origin));
        populateSpinner(list, (Spinner) findViewById(R.id.spinnerEthnicGroup));
    }

    private void populateMaritalStatus() {
        String [] items = getResources().getStringArray(R.array.marital_status);
        List<String> list = Arrays.asList(items);
        list = new ArrayList<>(list);
        list.add(FIRST_POSITION, getResources().getString(R.string.select_marital_status));
        populateSpinner(list, (Spinner) findViewById(R.id.spinnerMaritalStatus));
    }

    private void populateEducationLevel() {
        String [] items = getResources().getStringArray(R.array.education_level);
        List<String> list = Arrays.asList(items);
        list = new ArrayList<>(list);
        list.add(FIRST_POSITION, getResources().getString(R.string.select_education_level));
        populateSpinner(list, (Spinner) findViewById(R.id.spinnerEducationLevel));
    }

    private void populateWorkingSituation() {
        String [] items = getResources().getStringArray(R.array.working_situation);
        List<String> list = Arrays.asList(items);
        list = new ArrayList<>(list);
        list.add(FIRST_POSITION, getResources().getString(R.string.select_working_situation));
        populateSpinner(list, (Spinner) findViewById(R.id.spinnerWorkingSituation));
    }

    private void populateSalaryPerYear() {
        String [] items = getResources().getStringArray(R.array.salary_per_year);
        List<String> list = Arrays.asList(items);
        list = new ArrayList<>(list);
        list.add(FIRST_POSITION, getResources().getString(R.string.select_salary_per_year));
        populateSpinner(list, (Spinner) findViewById(R.id.spinnerSalaryPerYear));
    }

    private void populateDisabilityLevel() {
        String [] items = getResources().getStringArray(R.array.disability_level);
        List<String> list = Arrays.asList(items);
        list = new ArrayList<>(list);
        list.add(FIRST_POSITION, getResources().getString(R.string.select_disability_level));
        populateSpinner(list, (Spinner) findViewById(R.id.spinnerDisabilityLevel));
    }

    private void populateSpinner(final List<String> optionsList, final Spinner spinner){
        ArrayAdapter<String> dataAdapter = getCustomHintAdapter(optionsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @NonNull
    private ArrayAdapter<String> getCustomHintAdapter(final List<String> list) {
        /* OBS: Population of the spinner using a customized ArrayAdapter that hides the first (dummy) entry
        / (Android) does NOT allow Spinner hints by default, thus the following workaround*/
        return new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v = null;

                //Using 0 as the initial dummy entry, we make it hidden
                if (position == FIRST_POSITION) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                }
                else {
                    //Passing convertView as null to prevent reuse of special case views
                    v = super.getDropDownView(position, null, parent);
                }

                //As well, hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };
    }

    private void setupRegisterButton() {
        final Button registerButton = (Button) findViewById(R.id.buttonRegisterScreen);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Register pressed", Toast.LENGTH_SHORT).show();

                //  TODO Esto coge los datos, seguir programandolo
                EditText mEmail = (EditText)findViewById(R.id.editText_enter_email_register_screen);
                EditText mPassword = (EditText) findViewById(R.id.editText_enter_password_register_screen);
                EditText mRepeatedPassword = (EditText) findViewById(R.id.editText_enter_password_repeat_register_screen);

                RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
                int radioButtonID = radioGroupGender.getCheckedRadioButtonId();
                View radioButton = radioGroupGender.findViewById(radioButtonID);
                int index = radioGroupGender.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) radioGroupGender.getChildAt(index);
                String gender = (String) btn.getText();

                String email = mEmail.getText().toString();
                Email email1 = new Email(email, "", getApplicationContext());
                email1.setApplicationContext(getApplicationContext());

                Toast.makeText(getApplicationContext(), "Gender: "+gender+". Email: "+email, Toast.LENGTH_SHORT).show();

                /*Intent myIntent = new Intent(InitialActivity.this, RegisterActivity.class);
                EditText mEmail = (EditText)findViewById(R.id.editTextEmail);
                EditText mPassword = (EditText)findViewById(R.id.editTextPassword);

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                myIntent.putExtra("email", email); //Optional parameters
                myIntent.putExtra("password", password); //Optional parameters

                InitialActivity.this.startActivity(myIntent);*/
            }
        });
    }


    private void retrieveEmailAndPassword() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String email = extras.getString("email");
            String password = extras.getString("password");
            Toast.makeText(getApplicationContext(), "Email: "+email+".\n Password: "+password, Toast.LENGTH_SHORT).show();
        }
    }
}