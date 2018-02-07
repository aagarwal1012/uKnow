package com.stackoverflow.uknow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.stackoverflow.uknow.Classes.UserCredentials;

import java.io.IOException;
import java.util.UUID;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText _nameText;
    EditText _emailText;
    EditText _mobileText;
    EditText _passwordText;
    EditText _reEnterPasswordText;
    Button _signupButton;
    TextView _loginLink;
    CircleImageView profile_image;
    ImageView profile_image_btn;
    LinearLayout activity_sign_up;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private Uri filepath;
    private Uri imageUrl;

    Snackbar snackbar;

    final static int  PICK_IMAGE_REQUEST = 10;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _nameText = (EditText) findViewById(R.id.input_name);
        _emailText = (EditText)  findViewById(R.id.input_email);
        _mobileText = (EditText) findViewById(R.id.input_mobile);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _reEnterPasswordText = (EditText) findViewById(R.id.input_reEnterPassword);
        _signupButton = (Button) findViewById(R.id.btn_signup);
        _loginLink = (TextView) findViewById(R.id.link_login);
        activity_sign_up = (LinearLayout) findViewById(R.id.linear_layout);

        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        profile_image_btn = (ImageView) findViewById(R.id.profile_image_btn);

        //Initilize Firebase Auth
        auth = FirebaseAuth.getInstance();

        //Initilize Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //Initilize Firebase Storage
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        profile_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String mobile = _mobileText.getText().toString();
        final String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        // TODO: Implement your own signup logic here.

        if (filepath != null){
            Long time = System.currentTimeMillis()/1000;
            final String temp = time.toString();
            StorageReference ref = storageReference.child("images/profilePics/" + _emailText.getText().toString() + "/" + temp);
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // Get url to the uploaded content;
                    imageUrl = taskSnapshot.getDownloadUrl();
                    UserCredentials userCredentials = new UserCredentials(name, email, password, imageUrl.toString(), mobile);
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 2000);
                    signUpUser(userCredentials);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Uploading DP", "onFailure: ", e);
                }
            });

        }
        else {
            UserCredentials userCredentials = new UserCredentials(name, email, password, null, mobile);
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 2000);
            signUpUser(userCredentials);
        }



    }

    public void signUpUser(final UserCredentials userCredentials){
        auth.createUserWithEmailAndPassword(userCredentials.getEmail(),userCredentials.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            snackbar = Snackbar.make(activity_sign_up,"Error: "+task.getException(),Snackbar.LENGTH_SHORT);
                            Log.d("Error", "Error ! " + task.getException());
                            snackbar.show();
                        }
                        else
                        {
                            if(userCredentials.getPicUrl() != null){
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(userCredentials.getName())
                                        .setPhotoUri(Uri.parse(userCredentials.getPicUrl())).build();
                                auth.getCurrentUser().updateProfile(profileUpdates);
                                databaseReference.child("User Credentials").child(auth.getCurrentUser().getUid()).setValue(userCredentials);
                            }
                            else {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userCredentials.getName()).build();
                                auth.getCurrentUser().updateProfile(profileUpdates);
                                databaseReference.child("User Credentials").child(auth.getCurrentUser().getUid()).setValue(userCredentials);
                            }
                            snackbar = Snackbar.make(activity_sign_up,"Register success! ",Snackbar.LENGTH_SHORT);
                            snackbar.show();

                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        }
                    }
                });
    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

    private void chooseImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent , "Select Picture") , PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Image set to imageView
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            filepath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                profile_image.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}