package com.stackoverflow.uknow.DesignationPredictor;



import com.stackoverflow.uknow.DesignationPredictor.Input.Sendinput;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.Output;
import com.stackoverflow.uknow.DesignationPredictor.Outputs.Results;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ayush on 05-Feb-18.
 */

public interface Designation_retrofitInterface {

    @POST("execute")

    Call<Output> getResult(@Query("api-version") String version, @Query("details") Boolean tru, @Body Sendinput sendinput);
    @POST("execute/")

    Call<Results> getResultTwo(@Query("api-version") String version, @Query("details") Boolean tru);


}
