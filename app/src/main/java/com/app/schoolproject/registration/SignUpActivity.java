package com.app.schoolproject.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.app.schoolproject.R;
import com.app.schoolproject.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;

import dmax.dialog.SpotsDialog;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText mName, mPhoneNumber, mEmail, mSemester,mRollNo,mPassword, mConfirmPassword;
    Button mSignUpBtn;
    TextView mSignInLink;
    ConstraintLayout rootLayout;
    private String name,phoneNumber, email,semester,rollNno,password,confirmPassword;
     SpotsDialog waitingDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        waitingDialog= (SpotsDialog) new SpotsDialog.Builder().setContext(this).setMessage("Registering...").build();
        initFirebaseInstances();
        signUpBtnClick();
        openLoginActivity();
    }

    private void openLoginActivity() {
        mSignInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void getTextFromEditTexts() {
        name = mName.getText().toString();
        phoneNumber = mPhoneNumber.getText().toString();
        email = mEmail.getText().toString();
        semester = mSemester.getText().toString();
        rollNno = mRollNo.getText().toString();
        password = mPassword.getText().toString();
        confirmPassword = mConfirmPassword.getText().toString();
    }


    private void signUpBtnClick() {


        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextFromEditTexts();


                if (TextUtils.isEmpty(name)){
                    mName.setError("Name is Required");

                }
                else if (!name.isEmpty() &&name.length()<4){
                    mName.setError("Name should be atleast 4 characters");
                }
               else   if (TextUtils.isEmpty(phoneNumber)){
                    mPhoneNumber.setError("Phone Number is required");
                }
                else if (phoneNumber.length() !=10){
                    mPhoneNumber.setError("Please enter valid Phone number");
                }
               else   if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                }
               else   if (TextUtils.isEmpty(semester)){
                    mSemester.setError("Semester is required");
                }
                else   if (semester.length() >2){
                    mSemester.setError("Please enter valid Semester");
                }
               else   if (TextUtils.isEmpty(rollNno)){
                    mRollNo.setError("Roll Number is required");
                }
               else if (rollNno.length() !=11){
                   mRollNo.setError("Please enter 11 digit roll number");
                }
              else    if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                }
               else   if (TextUtils.isEmpty(confirmPassword)){
                    mConfirmPassword.setError("Confirm password is required");
                }

               else if (password.length()<6 ){
                    mPassword.setError("Password should be atleast 6 characters");
                }
              else   if (!password.equals(confirmPassword)){
                    mConfirmPassword.setError("Passwords does not match");
                }
              else {

                  registerUser();
                       }

            }

            private void registerUser() {

                waitingDialog.show();



            }
        });
    }

    private void initFirebaseInstances() {

    }

    private void initViews() {
        rootLayout = findViewById(R.id.rootLayout);
        mEmail = findViewById(R.id.editTextEmail);
        mPassword = findViewById(R.id.editTextPassword);
        mConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        mSignUpBtn = findViewById(R.id.signUpButton);
        mSignInLink = findViewById(R.id.login_link);
    }
}
