package com.nareshgediya.retrofitbasic;


import androidx.annotation.ArrayRes;

import com.google.gson.annotations.Expose;
import com.nareshgediya.retrofitbasic.Models.Comment;
import com.nareshgediya.retrofitbasic.Models.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyWebService {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    String FEED = "posts";

Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

@GET(FEED)
    Call<List<Post>> getPost();

//this is path type .... Dynamic url with path
//@GET("posts/{id}/comments")
//    Call<List<Comment>> getComments(@Path("id") int userId);


    //This is Single Perameter Query type  ....Dynamic url with Query
//@GET("comments")
//    Call<List<Comment>> getComments(@Query("postId") int postId);



    //Using QueryMap
//@GET("comments")
//    Call<List<Comment>> getComments(@QueryMap Map<String, String> map);


    //This is Multiple Perameter Query type
    //here is A Array of int  id for get multiple ids data
    //make sure ids Array must be as last perameter
@GET("comments")
    Call<List<Comment>> getComments(
                                    @Query("_sort") String sortBy,
                                    @Query("_order") String orderby,
                                    @Query("id") int... ids );



@POST("posts")
    Call<Post> createPost(@Body Post post);




    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") int userId, @Field("title") String title, @Field("body") String body);


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, Object> map);

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

      @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

      @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);



}
