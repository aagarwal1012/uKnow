package com.stackoverflow.uknow.EmotionApi;

import com.stackoverflow.uknow.EmotionApi.Input.Url;
import com.stackoverflow.uknow.EmotionApi.Output.FaceApiResult;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface ApiRequestInterface {

    @POST("/recognize")
    Call<FaceApiResult> getFaceApiResutl(@Body Url url);

}
