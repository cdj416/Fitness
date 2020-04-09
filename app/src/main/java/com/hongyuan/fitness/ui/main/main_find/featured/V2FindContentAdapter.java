package com.hongyuan.fitness.ui.main.main_find.featured;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
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



        //如果当前视图比例未被存储，先去存储
        if(hashMap.size() <= helper.getAdapterPosition()){
            //获取宽高并计算比例
            String whStr = item.getCircle_img().substring((item.getCircle_img().indexOf("_")+1),item.getCircle_img().lastIndexOf("."));
            String[] wh = whStr.split("x");
            float ratio = Float.valueOf(wh[0])/Float.valueOf(wh[1]);
            hashMap.put(helper.getAdapterPosition(),ratio);
        }

        //获取比例并设置视图高度并加载图片
        setImgViewHeight(helper.getView(R.id.coverImg),(int) (useWidth/hashMap.get(helper.getAdapterPosition())), item.getCircle_img());


        //获取图片链接
        int imgNum = 0;
        String imgUrl = "";
        if(item.getCi() != null && item.getCi().size() > 0){
            imgNum = item.getCi().size();
            imgUrl = item.getCi().get(0).getFile_src();
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
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load(imgUrl).transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(coverImg);
    }

}
