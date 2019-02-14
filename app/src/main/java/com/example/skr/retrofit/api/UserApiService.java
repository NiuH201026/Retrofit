package com.example.skr.retrofit.api;

import com.example.skr.retrofit.bean.LoginBean;
import com.example.skr.retrofit.bean.SearchBean;
import com.example.skr.retrofit.bean.ShouYeBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApiService {
    @POST("small/user/v1/login")
    @FormUrlEncoded
    Call<LoginBean> login(@FieldMap HashMap<String,String> parms);
//    Call<UserApiService> login(@Field("mobile")String mobile,@Field("pwd")String pwd);

    @GET("small/commodity/v1/findCommodityByKeyword?&page=1&count=8")
    Call<SearchBean> shou(@Query("keyword")String key);
}
