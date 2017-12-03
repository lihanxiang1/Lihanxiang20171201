package com.bwie.lihanxiang20171201.interfaces;

import com.bwie.lihanxiang20171201.view.UserBean;

import retrofit2.http.GET;

/**
 * Created by muhanxi on 17/11/30.
 */

public interface IInterface {

//    http://api.tianapi.com/wxnew/?key=18e883dd6b316eb1d97fd86338abbf06&num=10
    //http://api.tianapi.com/social/?key=ac87b4a4e634eb4a4e96fd371490a667&num=10

    @GET("social/?key=ac87b4a4e634eb4a4e96fd371490a667&num=10")
    retrofit2.Call<UserBean> get();

}
