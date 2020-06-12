package com.hongyuan.fitness.ui.shop.sadapter;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.hongyuan.fitness.R;

import java.io.File;
import java.util.HashMap;

public class SDMimgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    //存储图片宽高比例
    private HashMap<Integer, Float> hashMap = new HashMap<>();

    //单列的宽度
    private int useWidth;

    public SDMimgAdapter(int useWidth){
        super(R.layout.item_sdm_imgs);
        this.useWidth = useWidth;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        //如果当前视图比例未被存储，先去存储
        if(hashMap.size() <= helper.getAdapterPosition()){
            try {
                //获取宽高并计算比例
                String whStr = item.substring((item.indexOf("_")+1),item.lastIndexOf("."));
                String[] wh = whStr.split("x");
                float ratio = Float.valueOf(wh[0])/Float.valueOf(wh[1]);
                hashMap.put(helper.getAdapterPosition(),ratio);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        //获取比例并设置视图高度并加载图片
        setImgViewHeight(helper.getView(R.id.longImg),(int) (useWidth/hashMap.get(helper.getAdapterPosition())), item);
    }

    /*
     * 设置图片控件高度
     * */
    private void setImgViewHeight(SubsamplingScaleImageView longImg, int height, String imgUrl){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) longImg.getLayoutParams();
        layoutParams.height = height;
        longImg.setLayoutParams(layoutParams);

        //下载图片保存到本地
        Glide.with(mContext).load(imgUrl).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                longImg.setImage(ImageSource.uri(resource.getAbsolutePath()));
            }
        });
    }
}
