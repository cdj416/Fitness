package com.hongyuan.fitness.ui.shop.sbeans;

public class AddProductReviewBeans {

    /**
     * gp_id : 141
     * evaluation_content : 很好的产品
     * is_anonymous : 0
     * evaluation_score : 4.0
     * evaluationfile : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg，http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
     */

    private int gp_id;
    private String evaluation_content;
    private int is_anonymous;
    private String evaluation_score;
    private String evaluationfile;

    public int getGp_id() {
        return gp_id;
    }

    public void setGp_id(int gp_id) {
        this.gp_id = gp_id;
    }

    public String getEvaluation_content() {
        return evaluation_content;
    }

    public void setEvaluation_content(String evaluation_content) {
        this.evaluation_content = evaluation_content;
    }

    public int getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(int is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public String getEvaluation_score() {
        return evaluation_score;
    }

    public void setEvaluation_score(String evaluation_score) {
        this.evaluation_score = evaluation_score;
    }

    public String getEvaluationfile() {
        return evaluationfile;
    }

    public void setEvaluationfile(String evaluationfile) {
        this.evaluationfile = evaluationfile;
    }
}
