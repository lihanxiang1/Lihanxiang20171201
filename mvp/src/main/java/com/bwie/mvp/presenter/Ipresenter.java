package com.bwie.mvp.presenter;

import com.bwie.mvp.bean.NewslistBean;
import com.bwie.mvp.model.UserModel;
import com.bwie.mvp.view.Iview;

import java.util.List;

/**
 * Created by Lonely on 2017/12/3.
 */
public class Ipresenter implements UserModel.onFinish {
    public final Iview iview;
    public final UserModel userModel;

    public Ipresenter(Iview iview) {
        this.iview = iview;
        userModel = new UserModel(this);
    }

    public void getnews(String url){
        userModel.suibian(url);
    }

    @Override
    public void setonsuibian(List<NewslistBean> list) {
        iview.getData(list);
    }
}
