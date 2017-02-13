package com.yoyoyt.rxjavademo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者: 刘净辉
 * 日期: 2017/2/13 11:20.
 */

public interface Users {

    @GET("top250")
    Observable<User> getUser(@Query("start") int start, @Query("count") int count);
}
