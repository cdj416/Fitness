package com.hongyuan.fitness.ui.person.physical_data.silhouette;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.DividerItemDecoration;

public class SihouetteAdapter extends BaseQuickAdapter<SihouetteBeans.DataBean.ListBean, BaseViewHolder> {

    public interface OnItemClickListener{
        void itemClick(View view, int partPosition, int childPosition);
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public SihouetteAdapter(){
        super(R.layout.item_sihouette);
    }

    @Override
    protected void convert(BaseViewHolder helper, SihouetteBeans.DataBean.ListBean item) {

        helper.setText(R.id.timeText, item.getImg_date());

        RecyclerView mRecycler = helper.getView(R.id.imgRec);

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                mContext, DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        mRecycler.setLayoutManager(layoutManager);
        SihouetteChildAdapter adapter = new SihouetteChildAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(item.getImgs());
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            if(onItemClickListener != null){
                onItemClickListener.itemClick(view,helper.getPosition(),position);
            }
        });

    }

}
