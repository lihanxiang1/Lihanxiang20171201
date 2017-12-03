package com.bwie.lihanxiang20171201;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lihanxiang20171201.adapter.MyAdapter;
import com.bwie.lihanxiang20171201.interfaces.IInterface;
import com.bwie.lihanxiang20171201.view.NewslistBean;
import com.bwie.lihanxiang20171201.view.UserBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.edit_id)
    EditText editId;
    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.button_insert)
    Button buttonInsert;
    @Bind(R.id.button_delete)
    Button buttonDelete;
    @Bind(R.id.button_select)
    Button buttonSelect;
    @Bind(R.id.text_jieguo)
    TextView textView;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;

    private MyAdapter myAdapter;
    private List<NewslistBean> newslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();

        IApplication.session.getNewslistBeanDao().deleteAll();
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tianapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IInterface iInterface = retrofit.create(IInterface.class);

        iInterface.get().enqueue(new Callback<UserBean>() {

            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {

                newslist = new ArrayList<>();
                newslist.addAll(response.body().getNewslist());

//                        notifyDataSetChanged();

                System.out.println("newslist = " + newslist.get(1).getTitle());

                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);

                myAdapter = new MyAdapter(MainActivity.this , newslist);

                // 保存到数据库
                IApplication.session.getNewslistBeanDao().insertInTx(newslist);
                //添加数据库吐司
                Toast.makeText(MainActivity.this,"-------"+IApplication.session.getNewslistBeanDao().loadAll(),Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(myAdapter);
//                        System.out.println("user = " + user.getNewslist().get(1).getTitle());
//                        Toast.makeText(MainActivity.this ,user.getNewslist().get(1).getTitle(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.button_insert, R.id.button_delete, R.id.button_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_insert:
                if (editId.getText()!=null&&editName.getText()!=null){
//                    private String ctime;
//                    private String title;
//                    private String description;
//                    private String picUrl;
//                    private String url;

                    NewslistBean bean = new NewslistBean(null,editName.getText().toString(),null,null,null);
                    IApplication.session.getNewslistBeanDao().insert(bean);
//                    textView.setText(IApplication.session.getNewslistBeanDao().loadAll().toString());
                    Toast.makeText(MainActivity.this,""+IApplication.session.getNewslistBeanDao().loadAll(),Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.button_delete:
                int num = Integer.parseInt(editId.getText().toString());
//                DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(IApplication.getContext(), "notes-db", null);
//                DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
//                DaoSession daoSession = daoMaster.newSession();
//                NewslistBeanDao userDao = daoSession.getNewslistBeanDao();
//                NewslistBeanDao findUser = userDao.queryBuilder().where(NewslistBeanDao.Properties.Name.eq("wyk")).build().unique();
//                if(findUser != null){
//                    userDao.deleteByKey(findUser.getId());
//                }
//                IApplication.session.getNewslistBeanDao().deleteByKey(1l);
                break;
            case R.id.button_select:

            List<NewslistBean> newslistBeen = IApplication.session.getNewslistBeanDao().loadAll();

            newslist = null;

            for (int i = 0; i < newslistBeen.size(); i++) {

            }
            break;
        }
    }

//    @OnClick({R.id.button_insert, R.id.button_delete, R.id.button_select})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.button_insert:
//                mUser = new User((long) 2, "anye3");
//                mUserDao.insert(mUser);//添加一个
//                break;
//            case R.id.button_delete:
//                mUserDao.deleteByKey(mUser.getId());
//                break;
//            case R.id.button_select:
//                List<User> users = mUserDao.loadAll();
//                String userName = "";
//                for (int i = 0; i < users.size(); i++) {
//                    userName += users.get(i).getName()+",";
//                }
//                listView.setText("查询全部数据==>"+userName);
//                break;
//        }
//    }
}
