package com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class V3HomeStarCoachAdapter extends BaseQuickAdapter<VtwoStarCoachBean.DataBean.ListBean, BaseViewHolder> {

    public V3HomeStarCoachAdapter(){
        super(R.layout.item_vthird_home_start_coach);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoStarCoachBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getCoach_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.coachName,item.getCoach_nickname())
                .setText(R.id.classNum,"累计上课"+BaseUtil.getNoZoon(item.getTotal_course())+"节")
                .setText(R.id.classPrice, BaseUtil.getNoZoon(item.getCp_price()))
                .setText(R.id.goodType,"擅长："+item.getFt_str());

        if(helper.getPosition() == 0){
            helper.setBackgroundRes(R.id.itemBg,R.drawable.shape_radius6_top_ffffff);
        }else{
            helper.setBackgroundColor(R.id.itemBg,mContext.getResources().getColor(R.color.color_FFFFFF));
        }
        helper.addOnClickListener(R.id.itemBg);
    }
}
