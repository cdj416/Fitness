package com.hongyuan.fitness.custom_view.filter_view.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.filter_view.util.UIUtil;
import com.hongyuan.fitness.custom_view.filter_view.view.FilterCheckedTextView;

import java.util.List;

/**
 * 菜单条目适配器
 */
public abstract class DoubleListAdapter<T> extends BaseBaseAdapter<T> {

    private final LayoutInflater inflater;

    public DoubleListAdapter(List<T> list, Context context) {
        super(list, context);
        inflater = LayoutInflater.from(context);
    }

    public static class FilterItemHolder {
        LinearLayout itemBox;
        ImageView statusImg;
        TextView showText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FilterItemHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_filter_double_right, parent, false);

            holder = new FilterItemHolder();
            holder.itemBox = convertView.findViewById(R.id.itemBox);
            holder.statusImg = convertView.findViewById(R.id.statusImg);
            holder.showText = convertView.findViewById(R.id.showText);

            holder.showText.setPadding(0, UIUtil.dp(context, 15), 0, UIUtil.dp(context, 15));
            initCheckedTextView(holder.itemBox);

            convertView.setTag(holder);
        } else {
            holder = (FilterItemHolder) convertView.getTag();
        }

        T t = list.get(position);
        holder.showText.setText(provideText(t));
        if(isSelect(t)){
            holder.statusImg.setImageResource(R.mipmap.filter_select_img);
            //holder.showText.setTextColor(context.getResources().getColor(R.color.color_EF5B48));
        }else{
            holder.statusImg.setImageResource(R.mipmap.filter_no_select);
            //holder.showText.setTextColor(context.getResources().getColor(R.color.color_FF333333));
        }

        return convertView;
    }

    public abstract String provideText(T t);
    public abstract boolean isSelect(T t);

    protected void initCheckedTextView(LinearLayout checkedTextView) {
        checkedTextView.setPadding(UIUtil.dp(context, 15), 0, 0, 0);
        checkedTextView.setBackgroundResource(android.R.color.white);
    }


}
