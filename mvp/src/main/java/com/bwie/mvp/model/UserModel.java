package com.bwie.mvp.model;

import com.bwie.mvp.bean.NewslistBean;
import com.bwie.mvp.bean.UserBean;
import com.bwie.mvp.callback.IInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lonely on 2017/12/3.
 */
public class UserModel implements Imodel {
    //http://api.tianapi.com/social/?key=ac87b4a4e634eb4a4e96fd371490a667&num=10

    private onFinish onfinish;

    public UserModel(UserModel.onFinish onfinish) {
        this.onfinish = onfinish;
    }

    @Override
    public void suibian(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
        IInterface iInterface = retrofit.create(IInterface.class);
        Call<UserBean> userBeanCall = iInterface.get();
        userBeanCall.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                List<NewslistBean> newslist = response.body().getNewslist();
                onfinish.setonsuibian(newslist);
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

            }
        });
    }

    public interface onFinish{
        void setonsuibian(List<NewslistBean> list);
    }
}
