package com.example.day1;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    //https://www.wanandroid.com/project/list/1/json?cid=294
    String BASE_URL="https://www.wanandroid.com/project/";
    @GET("list/1/json?cid=294")
    Observable<WanBean> onDate();
}
