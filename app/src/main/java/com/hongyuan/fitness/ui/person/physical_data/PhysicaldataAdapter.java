package com.hongyuan.fitness.ui.person.physical_data;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class PhysicaldataAdapter extends BaseQuickAdapter<PhsicaldataSilhouetteDataBeans.DataBean.ListBean.ImgBean, BaseViewHolder> {

    public PhysicaldataAdapter(){
        super(R.layout.item_phsicaldata_silhouette);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhsicaldataSilhouetteDataBeans.DataBean.ListBean.ImgBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getImg()).apply(options).into((ImageView) helper.getView(R.id.img));

        if(helper.getPosition() == (getData().size() - 1) ){
            helper.setVisible(R.id.mengCheng,true).setVisible(R.id.goSihouetteBox,true)
                    .setText(R.id.imgNum,getData().size()+"");
        }else{
            helper.setVisible(R.id.mengCheng,false).setVisible(R.id.goSihouetteBox,false);
        }

        helper.addOnClickListener(R.id.box);
    }

}
