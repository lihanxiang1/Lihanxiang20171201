package com.bwie.mvp.app;

import android.app.Application;

import com.bwie.greendao.gen.DaoMaster;
import com.bwie.greendao.gen.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Lonely on 2017/12/3.
 */
public class MyAppcli extends Application {

    public static DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        DaoMaster.DevOpenHelper devOpenHelper =  new DaoMaster.DevOpenHelper(this,"1509D");

//        Database database = devOpenHelper.getEncryptedWritableDb("1509D");
        Database database =  devOpenHelper.getWritableDb();

        session = new DaoMaster(database).newSession();
    }
}
