package com.example.api.services.endpoints;

import com.example.api.services.dataResponse.InfoResponse;
import com.example.api.services.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InfoEndPoints {
    @Headers("code-app:2022*01")
    @GET("api/users")
    Call<InfoResponse> getInfo();

    @Headers("code-app:2022*01")
    @POST("api/users")
    Call<InfoResponse> postInfo(@Body User user);

    @Headers("code-app:2022*01")
    @DELETE("api/users/{id}")
    Call<InfoResponse> deleteInfo(@Path("id") String id);

    @Headers("code-app:2022*01")
    @PUT("api/users/{id}")
    Call<InfoResponse> updateInfo(@Path("id") String id, @Body User user);

    @Headers("code-app:2022*01")
    @GET("api/users/{id}")
    Call<InfoResponse> getDetailInfo(@Path("id") String id);
}
