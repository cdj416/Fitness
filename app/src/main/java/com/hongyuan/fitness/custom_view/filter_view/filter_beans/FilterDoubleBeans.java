package com.hongyuan.fitness.custom_view.filter_view.filter_beans;

import java.util.List;

public class FilterDoubleBeans {
    public String desc;
    public List<FilterRigthBeans> child;

    public static class FilterRigthBeans{
        public boolean select;
        public String showText;
    }
}
