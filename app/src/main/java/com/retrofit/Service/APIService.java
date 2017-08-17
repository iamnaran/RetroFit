package com.retrofit.Service;

import com.retrofit.Models.Event;
import com.retrofit.Models.Login;
import com.retrofit.Models.Post;
import com.retrofit.URLConstants.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by NaRan on 8/13/17.
 */

public interface APIService {

    @GET(Constants.EVENT_URL)
    Call<List<Event>> getEventsData();


    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Call<Login> userLogin(@Field("username") String username , @Field("password") String password);


    @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId);


}
