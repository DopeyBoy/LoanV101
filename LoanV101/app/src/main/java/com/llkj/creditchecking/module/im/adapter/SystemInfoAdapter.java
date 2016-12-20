package com.llkj.creditchecking.module.im.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llkj.creditchecking.R;
import com.llkj.creditchecking.base.BaseRecyclerAdapter;
import com.llkj.creditchecking.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class SystemInfoAdapter extends BaseQuickAdapter<NewsModel.ContentList> {

    public SystemInfoAdapter(List<NewsModel.ContentList> data) {
        super(R.layout.item_systeminfo, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsModel.ContentList contentList) {

//        baseViewHolder.setText(R.id.title, contentList.title)//
//                .setText(R.id.desc, contentList.desc)//
//                .setText(R.id.pubDate, contentList.pubDate)//
//                .setText(R.id.source, contentList.source);
//
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // WebActivity.runActivity(mContext, contentList.title, contentList.link);
            }
        });
//
//        NineGridView nineGrid = baseViewHolder.getView(R.id.nineGrid);
//        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
//        List<NewsModel.NewsImage> images = contentList.imageurls;
//        if (images != null) {
//            for (NewsModel.NewsImage image : images) {
//                ImageInfo info = new ImageInfo();
//                info.setThumbnailUrl(image.url);
//                info.setBigImageUrl(image.url);
//                imageInfo.add(info);
//            }
//        }
//        nineGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
//
//        if (images != null && images.size() == 1) {
//            nineGrid.setSingleImageRatio(images.get(0).width * 1.0f / images.get(0).height);
//        }
//    }

    }
}
