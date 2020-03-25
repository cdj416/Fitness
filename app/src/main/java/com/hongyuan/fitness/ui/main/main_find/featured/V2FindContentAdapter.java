package com.hongyuan.fitness.ui.main.main_find.featured;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;


public class V2FindContentAdapter extends BaseQuickAdapter<FeatureBean.DataBean.ListBean, BaseViewHolder> {

    //存储图片宽高比例
    private HashMap<Integer, Float> hashMap = new HashMap<>();
    //单列的宽度
    private int useWidth;

    public V2FindContentAdapter(int useWidth){
        super(R.layout.item_find_content_v2);
        this.useWidth = useWidth;
    }

    @Override
    protected void convert(final BaseViewHolder helper, FeatureBean.DataBean.ListBean item) {
        //加载头像图片
        Glide.with(mContext).load(item.getMi_head())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.default_head_img)
                        .error(R.mipmap.default_head_img)).into((RoundedImageView)helper.getView(R.id.headImg));

        /*****************************************瀑布流处理***************************************/

        //获取图片链接
        int imgNum = 0;
        String imgUrl = "";
        if(item.getCi() != null && item.getCi().size() > 0){
            imgNum = item.getCi().size();
            imgUrl = item.getCi().get(0).getFile_src();
        }

        //获取图片视图
        RoundedImageView coverImg = helper.getView(R.id.coverImg);
        //获取原始图片宽高比例并存储到集合中
        if(hashMap.get(helper.getAdapterPosition()) == null){
            String finalImgUrl = imgUrl;
            Glide.with(mContext)
                    .asBitmap()
                    .load(imgUrl)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            float ratio = Float.valueOf(resource.getWidth())/Float.valueOf(resource.getHeight());
                            if(hashMap.size() <= helper.getAdapterPosition()){
                                hashMap.put(helper.getAdapterPosition(),ratio);
                                setImgViewHeight(coverImg,(int) (useWidth/ratio), finalImgUrl);
                            }

                        }
                    });
        }else{
            //已存在比例就直接设置高度
            setImgViewHeight(coverImg, (int) (useWidth / hashMap.get(helper.getAdapterPosition())),imgUrl);
        }



        /*****************************************文字信息设置**************************************/

        helper.setText(R.id.styleImgNum,imgUrl.contains("mp4") ? "视频" : imgNum+"张")
                .setVisible(R.id.playMark,imgUrl.contains("mp4"))
                .setText(R.id.fName,item.getM_name())
                .setText(R.id.timeText,TimeUtil.friendly_time(item.getAdd_date()))
                .setText(R.id.attention, String.valueOf(item.getPraise_num()));

        if(item.getIs_praise() == 0){
            ViewChangeUtil.changeBottomDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeBottomDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_chengse_img);
        }

        helper.addOnClickListener(R.id.jumpDetails).addOnClickListener(R.id.attention);

    }

    /*
    * 设置图片控件高度
    * */
    private void setImgViewHeight(ImageView coverImg,int height,String imgUrl){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) coverImg.getLayoutParams();
        layoutParams.height = height;
        coverImg.setLayoutParams(layoutParams);

        //显示图片
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(imgUrl).apply(options).into(coverImg);
    }

}
