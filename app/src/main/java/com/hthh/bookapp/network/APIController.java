package com.hthh.bookapp.network;

import com.hthh.bookapp.model.ChapStory;
import com.hthh.bookapp.model.LinkImage;
import com.hthh.bookapp.model.StoryOfBookcase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIController {
    @GET("story_manager/layTruyen_doc.php")
    Call<List<StoryOfBookcase>> getStory(@Query("id_user") int id_user);

    @GET("story_manager/layChap.php")
    Call<List<ChapStory>> getChap(@Query("id") int id);

    @GET("story_manager/layAnh.php")
    Call<List<LinkImage>> getLinkImage(@Query("id_chap") int id_chap);
}
