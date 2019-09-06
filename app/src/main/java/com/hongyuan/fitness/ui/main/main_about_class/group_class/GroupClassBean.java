package com.hongyuan.fitness.ui.main.main_about_class.group_class;

import com.previewlibrary.enitity.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class GroupClassBean {

    public GroupClassBean(){
    }

    private List<UserViewInfo> imgs;

    public List<UserViewInfo> getImgs() {
        imgs = new ArrayList<>();
        imgs.add(new UserViewInfo("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg"));
        imgs.add(new UserViewInfo("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg"));
        imgs.add(new UserViewInfo("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg"));
        imgs.add(new UserViewInfo("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg"));
        return imgs;
    }
}
