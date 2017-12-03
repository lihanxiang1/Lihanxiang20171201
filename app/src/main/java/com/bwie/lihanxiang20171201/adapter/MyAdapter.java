package com.bwie.lihanxiang20171201.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.lihanxiang20171201.Main2Activity;
import com.bwie.lihanxiang20171201.R;
import com.bwie.lihanxiang20171201.view.EventBean;
import com.bwie.lihanxiang20171201.view.NewslistBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lonely on 2017/12/1.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.IViewHolder> {

    Context context;
    List<NewslistBean> list;

    public MyAdapter(Context context, List<NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, final int position) {
       // holder.itemSimpledraweeview.setImageURI(list.get(position).getPicUrl());
        holder.itemTitle.setText(list.get(position).getTitle());
        holder.itemTime.setText(list.get(position).getCtime());

        holder.itemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventBus.getDefault().postSticky(new EventBean(list.get(position).getPicUrl(), list.get(position).getTitle()));


                context.startActivity(new Intent(context, Main2Activity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

//    static class IViewHolder extends RecyclerView.ViewHolder {
//        SimpleDraweeView item_simpledraweeview;
//        TextView text_title;
//        TextView text_time;
//
//        IViewHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//            item_simpledraweeview = view.findViewById(R.id.item_simpledraweeview);
//            text_title = view.findViewById(R.id.item_title);
//            text_time = view.findViewById(R.id.item_time);
//        }
//    }

    static class IViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_simpledraweeview)
        SimpleDraweeView itemSimpledraweeview;
        @Bind(R.id.item_title)
        TextView itemTitle;
        @Bind(R.id.item_time)
        TextView itemTime;

        IViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
