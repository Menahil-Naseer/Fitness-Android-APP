package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public FirebaseAuth mAuth;
    Button signUpButton;
    EditText signUpEmailTextInput;
    EditText signUpPasswordTextInput;
    CheckBox agreementCheckBox;
    TextView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        signUpEmailTextInput = findViewById(R.id.editTextTextEmailAddress);
        signUpPasswordTextInput = findViewById(R.id.editTextTextPassword);
        signUpButton = findViewById(R.id.signup_btn);
        agreementCheckBox = findViewById(R.id.checkBox);
        errorView = findViewById(R.id.textView2);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (signUpEmailTextInput.getText().toString().contentEquals("")) {


                    errorView.setText("Email cannot be empty");


                } else if (signUpPasswordTextInput.getText().toString().contentEquals("")) {


                    errorView.setText("Password cannot be empty");


                } else if (!agreementCheckBox.isChecked()) {

                    errorView.setText("Please agree to terms and Condition");


                } else {


                    mAuth.createUserWithEmailAndPassword(signUpEmailTextInput.getText().toString(), signUpPasswordTextInput.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                try {
                                    if (user != null)
                                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "Email sent.");

                                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                            MainActivity.this);

                                                    // set title
                                                    alertDialogBuilder.setTitle("Please Verify Your EmailID");

                                                    // set dialog message
                                                    alertDialogBuilder
                                                            .setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                            .setCancelable(false)
                                                            .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {

                                                                    Intent signInIntent = new Intent(MainActivity.this, SignInActivity.class);
                                                                    startActivity(signInIntent);
                                                                }
                                                            });

                                                    // create alert dialog
                                                    AlertDialog alertDialog = alertDialogBuilder.create();

                                                    // show it
                                                    alertDialog.show();


                                                }
                                            }
                                        });


                                    } catch(Exception e){
                                        errorView.setText(e.getMessage());
                                    }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                                if (task.getException() != null) {
                                    errorView.setText(task.getException().getMessage());
                                }

                            }

                        }
                    });

                }

            }
        });


    }

    public void signinn(View view) {
        Intent in= new Intent(MainActivity.this,SignInActivity.class);
        startActivity(in);
    }
}
