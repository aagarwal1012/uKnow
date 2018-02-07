package com.stackoverflow.uknow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stackoverflow.uknow.EmotionApi.ApiRequestInterface;
import com.stackoverflow.uknow.EmotionApi.Input.Url;
import com.stackoverflow.uknow.EmotionApi.Output.FaceApiResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TakePicture extends AppCompatActivity {
    ImageView picture;
    Button take_pic;
    TextView textView;

    public static final String face_api_key = "75c91d16ec0c4348b9be6cc5f5858ed5";
    public static final String face_api_base_address = "https://westus.api.cognitive.microsoft.com/emotion/v1.0/";

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    Uri filepath;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        picture = (ImageView) findViewById(R.id.your_image);
        take_pic = (Button) findViewById(R.id.camera_btn);
        textView = (TextView) findViewById(R.id.text_view);

        //Firebase
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && mCurrentPhotoPath != null) {
            filepath = Uri.parse(mCurrentPhotoPath);
            Bitmap bitmap = null;
            bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            if (bitmap != null)
                picture.setImageBitmap(bitmap);
            galleryAddPic();
            
            uploadPic();
        }
    }

    private void uploadPic() {

        if (mCurrentPhotoPath != null){
            Long time = System.currentTimeMillis()/1000;
            final String temp = time.toString();
            StorageReference ref = FirebaseStorage.getInstance().getReference().child("images/test_images/" + firebaseUser.getEmail() + "/" + temp);
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // Get url to the uploaded content;
                    Uri imageUrl = taskSnapshot.getDownloadUrl();
                    Url url = new Url(imageUrl.toString());
                    apiRequest(url);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Uploading DP", "onFailure: ", e);
                }
            });

        }

    }

    private void apiRequest(Url url) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Ocp-Apim-Subscription-Key", face_api_key)
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(face_api_base_address)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiRequestInterface apiRequestInterface = retrofit.create(ApiRequestInterface.class);

        Gson gson = new Gson();
        Type type = new TypeToken<Url>() {}.getType();
        String json = gson.toJson(url, type);
        Log.d("json request",json);

        Call<FaceApiResult> call = apiRequestInterface.getFaceApiResutl(url);
        call.enqueue(new Callback<FaceApiResult>() {
            @Override
            public void onResponse(Call<FaceApiResult> call, retrofit2.Response<FaceApiResult> response) {
                Log.d("Face api response",String.valueOf(response.code()));
                FaceApiResult faceApiResult = response.body();
                Log.d("Received message : ", faceApiResult.getScores().getAnger());
            }

            @Override
            public void onFailure(Call<FaceApiResult> call, Throwable t) {

            }
        });

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


}
