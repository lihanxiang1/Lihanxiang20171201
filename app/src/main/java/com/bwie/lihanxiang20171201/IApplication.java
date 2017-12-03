package com.bwie.lihanxiang20171201;

import android.app.Application;
import android.content.Context;

import com.bwie.greendao.gen.DaoMaster;
import com.bwie.greendao.gen.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Lonely on 2017/12/1.
 */
public class IApplication extends Application {

    public static DaoSession session;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        DaoMaster.DevOpenHelper devOpenHelper =  new DaoMaster.DevOpenHelper(this,"1509D");

//        Database database = devOpenHelper.getEncryptedWritableDb("1509D");
        Database database =  devOpenHelper.getWritableDb();

        session = new DaoMaster(database).newSession();
    }

    public static Context getContext() {
        return mContext;
    }
}
