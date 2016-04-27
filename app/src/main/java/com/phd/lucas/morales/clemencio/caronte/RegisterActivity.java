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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.phd.lucas.morales.clemencio.caronte.domain.Email;
import com.phd.lucas.morales.clemencio.caronte.domain.Password;
import com.phd.lucas.morales.clemencio.caronte.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    public static final int UNSELECTED_GENDER = -1;

    Firebase firebaseRef = new Firebase("https://clemencio-morales-lucas-caronte.firebaseio.com");
    Firebase firebaseUsersRef = new Firebase("https://clemencio-morales-lucas-caronte.firebaseio.com/users");

    public static final int MAXIMUM_AGE = 100;
    public static final int MINIMUM_AGE = 10;
    public static final int FIRST_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupRegisterButton();
        retrieveEmailAndPassword();
        populateDropdowns();
    }

    //TODO: Pensar que hacer con el RegisterActivity (cuadradito azul) que aparece en esta pantalla al hacer scroll hacia abajo
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

                registerUser();
                //TODO REFACTOR OVER REGISTERING LOGIN
                //SEE ALL TODO COMMENTS, APPLICATE THEM AND CREATE PERSISTENCE LAYER FOR OBJECTS RETRIEVAL FROM DB


                //TODO Maybe here we should redireccionate to login or enter the application, making the login implicitly (second option more agile)
                //TODO Password encryptation procedures (with salt scheme) remain
                /*Intent myIntent = new Intent(InitialActivity.this, RegisterActivity.class);
                InitialActivity.this.startActivity(myIntent);*/
            }
        });
    }

    private void registerUser() {
        boolean success = true;
        final User user = new User();

        EditText mEmail = (EditText)findViewById(R.id.editText_enter_email_register_screen);
        String email = mEmail.getText().toString();

        EditText mPassword = (EditText) findViewById(R.id.editText_enter_password_register_screen);
        String password = mPassword.getText().toString();

        EditText mRepeatedPassword = (EditText) findViewById(R.id.editText_enter_password_repeat_register_screen);
        String repeatedPassword = mRepeatedPassword.getText().toString();

        try{
            user.setEmail(new Email(email));
            user.setPassword(new Password(password));
        } catch(Exception e){
            success = false;
            showToast(e.getMessage());
        }

        if(!password.equals(repeatedPassword)){
            success = false;
            showToast(getResources().getString(R.string.different_passwords));
        } else {
            RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
            int radioButtonID = radioGroupGender.getCheckedRadioButtonId();
            View radioButton = radioGroupGender.findViewById(radioButtonID);
            int index = radioGroupGender.indexOfChild(radioButton);

            if (index == UNSELECTED_GENDER){
                success = false;
                showToast(getResources().getString(R.string.gender_not_present));
            } else {
                RadioButton btn = (RadioButton) radioGroupGender.getChildAt(index);
                String gender = (String) btn.getText();
                user.setGender(gender);
            }

            Spinner ageSpinner =(Spinner) findViewById(R.id.spinnerAge);
            String ageSpinnerValue = ageSpinner.getSelectedItem().toString();
            if(ageSpinnerValue.equals(getResources().getString(R.string.select_age))){
                success = false;
                showToast(getResources().getString(R.string.age_not_selected));
            } else {
                int age = Integer.parseInt(ageSpinner.getSelectedItem().toString());
                user.setAge(age);
            }

            Spinner ethnicOriginSpinner =(Spinner) findViewById(R.id.spinnerEthnicGroup);
            String ethnicOriginValue = ethnicOriginSpinner.getSelectedItem().toString();
            if(ethnicOriginValue.equals(getResources().getString(R.string.select_ethnic_origin))){
                success = false;
                showToast(getResources().getString(R.string.ethnic_origin_not_selected));
            } else {
                user.setEthnicOrigin(ethnicOriginValue);
            }

            Spinner maritalStatusSpinner =(Spinner) findViewById(R.id.spinnerMaritalStatus);
            String maritalStatusValue = maritalStatusSpinner.getSelectedItem().toString();
            if(maritalStatusValue.equals(getResources().getString(R.string.select_marital_status))){
                success = false;
                showToast(getResources().getString(R.string.marital_status_not_selected));
            } else{
                user.setMaritalStatus(maritalStatusValue);
            }

            Spinner educationLevelSpinner =(Spinner) findViewById(R.id.spinnerEducationLevel);
            String educationLevelValue = educationLevelSpinner.getSelectedItem().toString();
            if(educationLevelValue.equals(getResources().getString(R.string.select_education_level))){
                success = false;
                showToast(getResources().getString(R.string.education_level_not_selected));
            } else{
                user.setEducationLevel(educationLevelValue);
            }

            Spinner workingSituationSpinner =(Spinner) findViewById(R.id.spinnerWorkingSituation);
            String workingSituationValue = workingSituationSpinner.getSelectedItem().toString();
            if(workingSituationValue.equals(getResources().getString(R.string.select_working_situation))){
                success = false;
                showToast(getResources().getString(R.string.working_situation_not_selected));
            } else {
                user.setWorkingSituation(workingSituationValue);
            }

            Spinner annualSalarySpinner =(Spinner) findViewById(R.id.spinnerSalaryPerYear);
            String annualSalaryValue = annualSalarySpinner.getSelectedItem().toString();
            if(annualSalaryValue.equals(getResources().getString(R.string.select_salary_per_year))){
                success = false;
                showToast(getResources().getString(R.string.annual_salary_not_selected));
            } else {
                user.setSalaryPerYear(annualSalaryValue);
            }

            Spinner disabilityLevelSpinner =(Spinner) findViewById(R.id.spinnerDisabilityLevel);
            String disabilityLevelValue = disabilityLevelSpinner.getSelectedItem().toString();
            if(disabilityLevelValue.equals(getResources().getString(R.string.select_disability_level))){
                success = false;
                showToast(getResources().getString(R.string.disability_level_not_selected));
            } else {
                user.setDisabilityLevel(disabilityLevelValue);
            }

            if(success){
                String userId = user.getEmail().getAddress();
                userId = Email.encodeID(userId);

                final String preparedUserId = userId;
                //TODO Create persistence layer in an independent object
                firebaseUsersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            showToast(getResources().getString(R.string.user_already_exists));
                        }
                        else {
                            showToast(getResources().getString(R.string.new_user_created));
                            Firebase firebaseUserReference = firebaseRef.child("users").child(preparedUserId);
                            firebaseUserReference.setValue(user);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        showToast(getResources().getString(R.string.firebase_error));
                    }
                });
            } else{
                showToast(getResources().getString(R.string.errors_in_the_form));
            }
        }
    }

    private void showToast(final String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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