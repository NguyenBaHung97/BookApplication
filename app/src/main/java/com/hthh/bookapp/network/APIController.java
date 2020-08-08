package com.hthh.bookapp.network;

import com.hthh.bookapp.model.ChapStory;
import com.hthh.bookapp.model.LinkImage;
import com.hthh.bookapp.model.Rate;
import com.hthh.bookapp.model.RateData;
import com.hthh.bookapp.model.Story;
import com.hthh.bookapp.model.StoryData;
import com.hthh.bookapp.model.StoryOfBookcase;
import com.hthh.bookapp.model.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIController {
    @GET("story_manager/getStoryTalk.php")
    Call<List<StoryOfBookcase>> getStory(@Query("id_user") int id_user);

    @GET("story_manager/getChap.php")
    Call<List<ChapStory>> getChap(@Query("id") int id);

    @GET("story_manager/getImage.php")
    Call<List<LinkImage>> getLinkImage(@Query("id_chap") int id_chap);

    @GET("story_manager/layType1.php")
    Call<List<Story>> getStoryByType(@Query("id_type") int id_type);

    @FormUrlEncoded
    @POST("story_manager/user_login.php")
    Call<UserData> login(@Field("email") String email,
                         @Field("password") String password);

    @FormUrlEncoded
    @POST("story_manager/user_signup.php")
    Call<UserData> signup(@Field("email") String email,
                          @Field("password") String password);

    @FormUrlEncoded
    @POST("story_manager/insert_story.php")
    Call<StoryData> insertStory(@Field("id_story") String id_story,
                                @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("story_manager/delete_story.php")
    Call<StoryData> deleteStory(@Field("id_story") String id_story,
                                @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("story_manager/insert_rate.php")
    Call<RateData> insertRate(@Field("id_story") String id_story,
                              @Field("id_user") String id_user,
                              @Field("email") String email,
                              @Field("comment") String comment,
                              @Field("rating") float rating);

    @GET("story_manager/get_rate.php")
    Call<List<Rate>> getRate(@Query("id_story") int id_story);


}
