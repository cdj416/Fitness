package com.hongyuan.fitness.ui.promt_success;

import java.io.Serializable;
import java.util.List;

public class V3SuccessBeans implements Serializable {

    //跳转类型
    public enum TYPE {
        PRIVITECLASS,GROUPCLASS,BUYCARD,BUYGOODS
    }

    private TYPE type;
    private String titleText;
    private String showText;
    private String btn1Text;
    private String btn2Text;
    private List<ItemConten> itemContens;

    public List<ItemConten> getItemContens() {
        return itemContens;
    }

    public void setItemContens(List<ItemConten> itemContens) {
        this.itemContens = itemContens;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public String getBtn1Text() {
        return btn1Text;
    }

    public void setBtn1Text(String btn1Text) {
        this.btn1Text = btn1Text;
    }

    public String getBtn2Text() {
        return btn2Text;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public void setBtn2Text(String btn2Text) {
        this.btn2Text = btn2Text;
    }

    public static class ItemConten implements Serializable{
        private String itemTitle;
        private String content;

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
