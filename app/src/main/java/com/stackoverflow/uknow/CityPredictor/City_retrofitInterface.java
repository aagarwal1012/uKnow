package com.stackoverflow.uknow.CityPredictor;

import com.stackoverflow.uknow.DesignationPredictor.Input.Sendinput;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.Output;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ayush on 06-Feb-18.
 */

public interface City_retrofitInterface {

    @POST("execute")
    Call<Output> getResult(@Query("api-version") String version, @Query("details") Boolean tru, @Body Sendinput sendinput);

}
