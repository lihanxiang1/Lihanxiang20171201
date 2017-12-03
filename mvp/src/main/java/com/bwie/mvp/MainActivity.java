package com.bwie.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.mvp.adapter.MyAdapter;
import com.bwie.mvp.app.MyApp;
import com.bwie.mvp.bean.NewslistBean;
import com.bwie.mvp.presenter.Ipresenter;
import com.bwie.mvp.view.Iview;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Iview {

    Ipresenter ipresenter;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ipresenter = new Ipresenter(this);

        ipresenter.getnews(MyApp.url);

    }

    @Override
    public void getData(List<NewslistBean> newslistBeen) {
        System.out.println("newslistBean = " + newslistBeen.get(1).getTitle());
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recycleView.setLayoutManager(manager);
        MyAdapter myAdapter = new MyAdapter(MainActivity.this, newslistBeen);
        recycleView.setAdapter(myAdapter);
    }
}
