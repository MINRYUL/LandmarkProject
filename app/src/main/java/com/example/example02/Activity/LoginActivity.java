package com.example.example02.Activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.example02.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BasisActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.registerButton).setOnClickListener(onClickListener);
        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.resetButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginButton:
                    login();
                    break;
                case R.id.registerButton:
                    myStartActivity(RegisterActivity.class);
                    break;
                case R.id.resetButton:
                    myStartActivity(PasswordResetActivity.class);
                    break;
            }
        }
    };

    private void login() {
        String email = ((EditText) findViewById(R.id.emailText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();

        if (email.length() > 0 && password.length() > 0) {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인에 성공하였습니다.");
                                myStartActivity(MainActivity.class);
                            } else {
                                if (task.getException() != null)
                                    startToast(task.getException().toString());
                            }
                        }
                    });

        } else {
            Toast.makeText(this, "이메일 또는 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}