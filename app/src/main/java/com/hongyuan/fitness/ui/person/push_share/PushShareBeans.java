package com.hongyuan.fitness.ui.person.push_share;

import com.hongyuan.fitness.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

public class PushShareBeans extends BaseBean {


    /**
     * data : {"imgs":"http://www.hongyuangood.com/ka_share/1.jpg,http://www.hongyuangood.com/ka_share/2.jpg,http://www.hongyuangood.com/ka_share/3.jpg,http://www.hongyuangood.com/ka_share/4.jpg,http://www.hongyuangood.com/ka_share/5.jpg,http://www.hongyuangood.com/ka_share/6.jpg,http://www.hongyuangood.com/ka_share/7.jpg","note":"你未来的样子藏在你现在的努力里","download":"http://www.hongyuangood.com/ka_share/downapp.png"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgs : http://www.hongyuangood.com/ka_share/1.jpg,http://www.hongyuangood.com/ka_share/2.jpg,http://www.hongyuangood.com/ka_share/3.jpg,http://www.hongyuangood.com/ka_share/4.jpg,http://www.hongyuangood.com/ka_share/5.jpg,http://www.hongyuangood.com/ka_share/6.jpg,http://www.hongyuangood.com/ka_share/7.jpg
         * note : 你未来的样子藏在你现在的努力里
         * download : http://www.hongyuangood.com/ka_share/downapp.png
         */

        private String imgs;
        private String note;
        private String download;
        private List<ImgsBean> imgList;

        public List<ImgsBean> getImgList() {
            if(imgList == null){
                imgList = new ArrayList<>();
            }else{
                return imgList;
            }
            String[] imgArray = imgs.split(",");
            for(int i = 0 ; i<imgArray.length ; i++){
                ImgsBean imgsBean = new ImgsBean();
                imgsBean.setImgUrl(imgArray[i]);
                if(i == 0){
                    imgsBean.setSelect(true);
                }else{
                    imgsBean.setSelect(false);
                }
                imgList.add(imgsBean);
            }

            return imgList;
        }

        public void setImgList(List<ImgsBean> imgList) {
            this.imgList = imgList;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public static class ImgsBean{
            private String imgUrl;
            private boolean select;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }
        }
    }

}
