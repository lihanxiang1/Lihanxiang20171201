package com.bwie.lihanxiang20171201;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lihanxiang20171201.view.EventBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class Main2Activity extends AppCompatActivity {


    private SimpleDraweeView simpleDraweeView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.main02_simple);
        textView = (TextView) findViewById(R.id.main02_text_view);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void event(EventBean eventBean) {
        textView.setText(eventBean.getTitle());
        simpleDraweeView.setImageURI(eventBean.getUrl());
        Toast.makeText(this, ""+ eventBean.getUrl() + "  " + eventBean.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
