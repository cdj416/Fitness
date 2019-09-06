package com.hongyuan.fitness.base;

import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.MenuPrivateLessonsBean;

public class BaseDataSingle {
    private static BaseDataSingle dataSingle;
    private BaseDataSingle(){
    }

    public static BaseDataSingle getInstance(){
        if(dataSingle == null){
            dataSingle = new BaseDataSingle();
        }
        return dataSingle;
    }

    //选择百科类型时用的
    private MenuPrivateLessonsBean.DataBean bkType;

    public MenuPrivateLessonsBean.DataBean getBkType() {
        return bkType;
    }

    public void setBkType(MenuPrivateLessonsBean.DataBean bkType) {
        this.bkType = bkType;
    }
}
