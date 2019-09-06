package com.hongyuan.fitness.custom_view.filter_view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.filter_view.util.UIUtil;
import com.hongyuan.fitness.custom_view.filter_view.view.FilterCheckedTextView;

import java.util.List;

/**
 * 菜单条目适配器
 */
public abstract class SimpleTextAdapter<T> extends BaseBaseAdapter<T> {

    private final LayoutInflater inflater;

    public SimpleTextAdapter(List<T> list, Context context) {
        super(list, context);
        inflater = LayoutInflater.from(context);
    }

    public static class FilterItemHolder {
        FilterCheckedTextView checkedTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FilterItemHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_filter_single_list, parent, false);

            holder = new FilterItemHolder();
            holder.checkedTextView = (FilterCheckedTextView) convertView;
            holder.checkedTextView.setPadding(0, UIUtil.dp(context, 15), 0, UIUtil.dp(context, 15));
            initCheckedTextView(holder.checkedTextView);

            convertView.setTag(holder);
        } else {
            holder = (FilterItemHolder) convertView.getTag();
        }

        T t = list.get(position);
        holder.checkedTextView.setText(provideText(t));

        return convertView;
    }

    public abstract String provideText(T t);

    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
    }


}
