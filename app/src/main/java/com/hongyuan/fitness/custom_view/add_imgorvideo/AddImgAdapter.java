package com.hongyuan.fitness.custom_view.add_imgorvideo;

import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.find.circle.edit_post.FileBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddImgAdapter extends BaseQuickAdapter<FileBean, BaseViewHolder> {

    public AddImgAdapter(){
        super(R.layout.item_add_images);
    }

    @Override
    protected void convert(final BaseViewHolder helper, FileBean item) {
        if(item.getmFile() != null || BaseUtil.isValue(item.getFilePath())){
            helper.getView(R.id.defaultImg).setVisibility(View.GONE);

            helper.getView(R.id.contentImg).setVisibility(View.VISIBLE);
            helper.setVisible(R.id.closeImg,true);
            if(item.getmFile() != null){
                Glide.with(mContext).load(item.getmFile()).into((RoundedImageView)helper.getView(R.id.contentImg));
            }else{
                Glide.with(mContext).load(item.getFilePath()).into((RoundedImageView)helper.getView(R.id.contentImg));
            }

        }else{
            helper.getView(R.id.defaultImg).setVisibility(View.VISIBLE);

            helper.getView(R.id.contentImg).setVisibility(View.GONE);
            helper.setVisible(R.id.closeImg,false);
        }
        if(item.getFileType() != null && item.getFileType().contains("video")){
            helper.getView(R.id.playMark).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.playMark).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.closeImg)
                .addOnClickListener(R.id.contentImg)
                .addOnClickListener(R.id.defaultImg);
    }

}
