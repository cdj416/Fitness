package com.hongyuan.fitness.ui.main;

import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.LocationBean;

public class TokenSingleBean {

    public static TokenSingleBean tokenBean;

    private TokenSingleBean(){}

    public static TokenSingleBean getInstance(){
        if(tokenBean == null){
            tokenBean = new TokenSingleBean();
        }
        return tokenBean;
    }

    private int at_id;
    private String at_name;
    private String at_pwd;
    private String token;
    private String ntoken;
    private String randomnum;
    private String timespan;
    private String region_name;//使用城市；
    private String region_code;//使用城市编码

    //用户登录成功时获取的id
    private String m_id;
    private String m_mobile;
    private String role_id;
    private String headUrl;


    /*
     * 登录状态值
     * */
    public int getIslogin() {
        return BaseUtil.isValue(m_id) ? 2 : 1;
    }


    /*
    * 拼接所有用户信息参数
    * */
    public String getWebAllParams(String url){
        if(url.contains("?")){
            return "&m_id="+ this.m_id+"&m_mobile="+this.m_mobile+"&at_id="+this.at_id+"&token="
                    +this.token+"&region_name="+this.region_name+"&region_code="+this.region_code
                    +"&ntoken="+this.ntoken+"&randomnum="+this.randomnum+"&timespan="+this.timespan
                    +"&lat="+ LocationBean.getInstance().getLat()
                    +"&lng="+ LocationBean.getInstance().getLng()
                    +"&islogin="+ getIslogin();
        }else{
            return "?m_id="+ this.m_id +"&m_mobile="+this.m_mobile+"&at_id="+this.at_id+"&token="
                    +this.token+"&region_name="+this.region_name+"&region_code="
                    +this.region_code+"&ntoken="+this.ntoken+"&randomnum="
                    +this.randomnum+"&timespan="+this.timespan
                    +"&lat="+ LocationBean.getInstance().getLat()
                    +"&lng="+ LocationBean.getInstance().getLng()
                    +"&islogin="+ getIslogin();
        }
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getNtoken() {
        return ntoken;
    }

    public void setNtoken(String ntoken) {
        this.ntoken = ntoken;
    }

    public String getRandomnum() {
        return randomnum;
    }

    public void setRandomnum(String randomnum) {
        this.randomnum = randomnum;
    }

    public String getTimespan() {
        return timespan;
    }

    public void setTimespan(String timespan) {
        this.timespan = timespan;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public int getAt_id() {
        return at_id;
    }

    public void setAt_id(int at_id) {
        this.at_id = at_id;
    }

    public String getAt_name() {
        return at_name;
    }

    public void setAt_name(String at_name) {
        this.at_name = at_name;
    }

    public String getAt_pwd() {
        return at_pwd;
    }

    public void setAt_pwd(String at_pwd) {
        this.at_pwd = at_pwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_mobile() {
        return m_mobile;
    }

    public void setM_mobile(String m_mobile) {
        this.m_mobile = m_mobile;
    }



    /*
    * 清空登录信息
    * */
    public void clearToken(){
        this.m_id = null;
        this.m_mobile = null;
    }

}
