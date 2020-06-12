package com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthour.V4HomeBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.makeramen.roundedimageview.RoundedImageView;


public class V3HomeFindContentAdapter extends BaseQuickAdapter<V4HomeBeans.DataBean.CircleBean, BaseViewHolder> {

    public V3HomeFindContentAdapter(){
        super(R.layout.item_home_find_content_v3);
    }

    @Override
    protected void convert(final BaseViewHolder helper, V4HomeBeans.DataBean.CircleBean item) {

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getMi_head()).apply(new RequestOptions()
                .placeholder(R.mipmap.default_head_img)
                .error(R.mipmap.default_head_img))
                .into((RoundedImageView)helper.getView(R.id.headImg));



        Glide.with(mContext).load(item.getCircle_img()).apply(options).into((RoundedImageView)helper.getView(R.id.coverImg));

        helper.setText(R.id.styleImgNum,String.valueOf(item.getImg_num())).setText(R.id.fName,item.getM_name())
                .setText(R.id.timeText,TimeUtil.friendly_time(item.getAdd_date()))
                .setText(R.id.attention, String.valueOf(item.getPraise_num()))
                .setText(R.id.content,item.getCircle_content());

        if(item.getIs_praise() == 0){
            ViewChangeUtil.changeBottomDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeBottomDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_chengse_img);
        }

        helper.addOnClickListener(R.id.jumpDetails).addOnClickListener(R.id.attention);

    }

}
