package com.stackoverflow.uknow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.stackoverflow.uknow.Classes.Dashboard;

import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class UserProfile extends Fragment {

    ImageView profile_image, profile_image_btn, edit_name_btn;
    TextView email_view;
    EditText name;
    Button save;

    //Firebase Database
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    //Firebase Storage
    private FirebaseStorage storage;
    private StorageReference storageReference;

    FirebaseUser user;

    private Uri filepath;

    final static int  PICK_IMAGE_REQUEST = 10;

    public UserProfile() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_profile, container, false);

        profile_image = (ImageView) view.findViewById(R.id.profile_image);
        profile_image_btn = (ImageView) view.findViewById(R.id.profile_image_btn);
        edit_name_btn = (ImageView) view.findViewById(R.id.name_btn);

        email_view = (TextView) view.findViewById(R.id.email);

        name = (EditText) view.findViewById(R.id.name);

        save = (Button) view.findViewById(R.id.btn_save);

        //Firebase storage init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //Firebase database init
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        user = FirebaseAuth.getInstance().getCurrentUser();

        String user_name = null, email = null;
        Uri photoUrl = null;

        if(user != null){
            user_name = user.getDisplayName();
            photoUrl = user.getPhotoUrl();
            email = user.getEmail();
        }

        if (user_name != null){
            name.setText("" + user_name, TextView.BufferType.EDITABLE);
            name.setClickable(false);
        }
        else {
            name.setText("", TextView.BufferType.EDITABLE);
            name.setClickable(false);
        }

        if (photoUrl != null){
            Glide.with(this).load(photoUrl.toString()).into(profile_image);
        }
        else {
            profile_image.setImageResource(R.drawable.blank_profile_picture);
        }

        email_view.setText("" + email);

        profile_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        edit_name_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setClickable(true);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPic();
                updateName(name.getText().toString());
                Toast.makeText(getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    private void chooseImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent , "Select Picture") , PICK_IMAGE_REQUEST);

    }

    private void uploadPic() {

        if (filepath != null){
            final String temp = UUID.randomUUID().toString();
            StorageReference ref = storageReference.child("images/profilePics/" + user.getEmail() + "/" + temp);
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // Get url to the uploaded content;
                    Uri imageUrl = taskSnapshot.getDownloadUrl();
                    FirebaseDatabase.getInstance().getReference().child("User Credentials").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("picUrl").setValue(imageUrl.toString());
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setPhotoUri(imageUrl)
                            .build();
                    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("up", "User profile updated.");
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(" ", "onFailure: ", e);
                }
            });

        }

    }

    private void updateName(String name){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("up", "User profile updated.");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Image set to imageView
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            filepath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filepath);
                profile_image.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }


}
