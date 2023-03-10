package com.resolvethecompany.iwilllawncare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity {

    VideoView videoview;
    Uri uri;

    FirebaseAuth mAuth;

    Button createAccountButton;
    EditText email_EditText,
            password_EditText,
            confirmPassword_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        createAccountButton = findViewById(R.id.createAccount_Button);
        videoview = findViewById(R.id.videoView);

        email_EditText = findViewById(R.id.usernameEditText);
        password_EditText = findViewById(R.id.password_EditText);
        confirmPassword_EditText = findViewById(R.id.confirmPassword_EditText);
    }

    @Override
    protected void onStart(){
        super.onStart();

        background_video_Method();

        //Create account
        createAccount_Handle();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null){
            Intent toCreateAccountActivity = new
                    Intent(Login_Activity.this,
                    Services_Activity.class);
            startActivity(toCreateAccountActivity);
        }else{
            Snackbar.make(getWindow().getDecorView().getRootView(),
                    "Create Account or log in",
                    BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }

    private void background_video_Method(){
        videoview = findViewById(R.id.videoView);
        uri = Uri.parse("android.resource://"
                +getPackageName()+"/"
                +R.raw.lawncare);
        videoview.setVideoURI(uri);
        videoview.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            videoview.start();
        });
    }

    private void createAccount_Handle(){
        createAccountButton.setOnClickListener(view -> {
            if(password_EditText.getText().toString()
                    .matches(confirmPassword_EditText
                            .getText().toString().trim())){
                CreateUser_Method(
                        email_EditText.getText().toString().trim(),
                        confirmPassword_EditText.getText().toString().trim());
            }
        });
    }

    private void CreateUser_Method(String email, String password){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the
                        // signed-in user's information
                        Log.d("Create_Account_Success",
                                "createUserWithEmail:success");
                        Intent toCreateAccountActivity = new
                                Intent(Login_Activity.this,
                                Services_Activity.class);
                        startActivity(toCreateAccountActivity);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Create_Account_Failed",
                                "createUserWithEmail:failure", task.getException());
                        Toast.makeText(this,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed(){
        //Do nothing
    }
}