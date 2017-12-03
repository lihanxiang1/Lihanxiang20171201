package com.bwie.mvp.callback;

import com.bwie.mvp.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lonely on 2017/12/3.
 */
public interface IInterface {
    @GET("social/?key=ac87b4a4e634eb4a4e96fd371490a667&num=10")
    Call<UserBean> get();
}
