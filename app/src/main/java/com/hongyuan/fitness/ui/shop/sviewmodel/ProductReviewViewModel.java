package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityProductReviewBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.MoreImgBean;
import com.hongyuan.fitness.ui.shop.sadapter.ProductReviewAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.AddProductReviewBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ProductReviewBeans;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductReviewViewModel extends CustomViewModel {

    private ActivityProductReviewBinding binding;

    private ProductReviewAdapter adapter;

    private List<ProductReviewBeans.DataBean> mList;

    public ProductReviewViewModel(CustomActivity mActivity, ActivityProductReviewBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        //设置智能选择图片
        binding.imgVideo.flag = true;

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new ProductReviewAdapter();
        binding.mRec.setAdapter(adapter);

        binding.submit.setOnClickListener(v -> {
            submitFiles();
        });

        binding.niMingBox.setOnClickListener(v -> {
            if("1".equals(binding.niMingBox.getTag().toString())){
                binding.checkImg.setImageResource(R.mipmap.yuanquanquan_no_select_img);
                binding.niMingBox.setTag("0");
            }else{
                binding.checkImg.setImageResource(R.mipmap.checkmark_circle);
                binding.niMingBox.setTag("1");
            }
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("o_id",getBundle().getString("o_id"));
        Controller.myRequest(Constants.GET_ORDER_GOODS,Controller.TYPE_POST,getParams(), ProductReviewBeans.class,this);
    }

    /*
     * 上传图片
     * */
    public void submitFiles(){
        if(!isValue(binding.editContent.getText().toString())){
            CustomDialog.showMessage(mActivity,"请输入评论内容！");
            return;
        }

        if(binding.imgVideo.getImgList() == null || binding.imgVideo.getImgList().size() <= 0){
            CustomDialog.showMessage(mActivity,"请选择图片！");
            return;
        }
        updataFile();
    }

    /*
     * 开始上传文件
     * */
    private void updataFile(){
        if(binding.imgVideo.getImgList() != null && binding.imgVideo.getImgList().size() > 0){
            onLoading("正在努力的上传数据，请耐心等待！");
            Controller.myRequest(Constants.UPFILE_OSS_MORE,Controller.TYPE_POST,getKeyValueList(binding.imgVideo.getImgList()), MoreImgBean.class,this);
        }
    }

    /*
    * 开始添加评论
    * */
    private void addReview(String fileUrls){
        clearParams().setParams("gp_id",String.valueOf(mList.get(0).getGp_id()))
                .setParams("o_id",getBundle().getString("o_id"))
                .setParams("evaluation_json",getJsonData(fileUrls));

        Controller.myRequest(ConstantsCode.ADD_GOODS_EVALUTION,Constants.ADD_GOODS_EVALUTION,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 组装json数据
    * */
    private String getJsonData(String fileUrls){
        List<AddProductReviewBeans> jsonList = new ArrayList<>();
        for(int i = 0 ; i < mList.size() ; i++){
            AddProductReviewBeans reviewBeans = new AddProductReviewBeans();
            reviewBeans.setGp_id(mList.get(i).getGp_id());
            reviewBeans.setEvaluation_score(String.valueOf(binding.myRat.getRating()));
            reviewBeans.setEvaluation_content(binding.editContent.getText().toString());
            reviewBeans.setEvaluationfile(fileUrls);
            reviewBeans.setIs_anonymous(Integer.parseInt(binding.niMingBox.getTag().toString()));

            jsonList.add(reviewBeans);
        }

        return GsonUtil.toJsonStr(jsonList);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof ProductReviewBeans){
            mList = ((ProductReviewBeans)data).getData();

            adapter.setNewData(mList);
        }

        if(data instanceof MoreImgBean && isSuccess(data)){
            MoreImgBean imgBeans = (MoreImgBean)data;

            String imgUrls = "";

            for(MoreImgBean.DataBean.FileUrlBean imgBean : imgBeans.getData().getFile_url()){
                imgUrls+=","+imgBean.getOss_url();
            }

            addReview(imgUrls);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.ADD_GOODS_EVALUTION){
            mActivity.showSuccess("评论成功！");
        }
    }
}
