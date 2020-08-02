package com.hthh.bookapp.api;

import android.os.AsyncTask;

import com.hthh.bookapp.Key;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class Apirun extends AsyncTask<Void,Void,Void> {
    String data;
    String sql;
    AddStory addStory;


    public Apirun(String sql, AddStory addStory) {
        this.sql = sql;
        this.addStory = addStory;
        this.addStory.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient ( );
        RequestBody requestBody = new FormEncodingBuilder()
                .add("sql", sql)
                .build ();
        Request request =new Request.Builder ()
                .url (Key.DOMAIN + Key.SQL)
                .post(requestBody)
                .build ();
        data =null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();

        }catch ( IOException e){
            data =null;
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(data==null){
            this.addStory.error();
        }else {
            if(data.equals ("ok")){
                this.addStory.finish(data);
            }
            else {
                this.addStory.error();
            }
        }
    }
}