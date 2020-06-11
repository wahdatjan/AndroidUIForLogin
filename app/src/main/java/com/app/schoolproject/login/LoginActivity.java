package com.app.schoolproject.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.schoolproject.R;
import com.app.schoolproject.registration.SignUpActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText mEmail,mPassword ;
    Button mLoginBtn;
    TextView mSignUpLink;
    ConstraintLayout rootLayout;
    private String  email,password;
    SpotsDialog waitingDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
         waitingDialog= (SpotsDialog) new SpotsDialog.Builder().setContext(this).setMessage("Logging In...").build();
        loginBtnClick();
        goToSignUpActivity();
    }

    private void goToSignUpActivity() {
        mSignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });
    }

    private void loginBtnClick() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextFromEditTexts();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                }
                else    if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                }

                else if (password.length()<6 ){
                    mPassword.setError("Password should be atleast 6 characters");
                }
                else {
                    doLogin();
                }
            }

            private void doLogin() {
                waitingDialog.show();

            }
        });
    }

    private void getTextFromEditTexts() {

        email = mEmail.getText().toString();
        password = mPassword.getText().toString();
    }
    private void initViews() {
        mLoginBtn = findViewById(R.id.login_button);
        mEmail = findViewById(R.id.editTextEmail);
        mPassword = findViewById(R.id.editTextPassword);
        rootLayout = findViewById(R.id.rootLayout);
        mSignUpLink = findViewById(R.id.signUpLink);
    }
}