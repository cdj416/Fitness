package com.hongyuan.fitness.custom_view.time_selecter.get_address;

import java.io.Serializable;
import java.util.List;

public class JsonDataBean implements Serializable {
    private List<JsonFileBean.DataBean> provinceList;
    private List<List<JsonFileBean.DataBean.ChildBeanX>> cityList;
    private List<List<List<JsonFileBean.DataBean.ChildBeanX.ChildBean>>> areaList;

    public List<JsonFileBean.DataBean> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<JsonFileBean.DataBean> provinceList) {
        this.provinceList = provinceList;
    }

    public List<List<JsonFileBean.DataBean.ChildBeanX>> getCityList() {
        return cityList;
    }

    public void setCityList(List<List<JsonFileBean.DataBean.ChildBeanX>> cityList) {
        this.cityList = cityList;
    }

    public List<List<List<JsonFileBean.DataBean.ChildBeanX.ChildBean>>> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<List<List<JsonFileBean.DataBean.ChildBeanX.ChildBean>>> areaList) {
        this.areaList = areaList;
    }

    private String useProCode;
    private String usecityCode;
    private String useAreaCode;
    private String usePro;
    private String useCity;
    private String useArea;

    public String getUseProCode() {
        return useProCode;
    }

    public void setUseProCode(String useProCode) {
        this.useProCode = useProCode;
    }

    public String getUsecityCode() {
        return usecityCode;
    }

    public void setUsecityCode(String usecityCode) {
        this.usecityCode = usecityCode;
    }

    public String getUseAreaCode() {
        return useAreaCode;
    }

    public void setUseAreaCode(String useAreaCode) {
        this.useAreaCode = useAreaCode;
    }

    public String getUsePro() {
        return usePro;
    }

    public void setUsePro(String usePro) {
        this.usePro = usePro;
    }

    public String getUseCity() {
        return useCity;
    }

    public void setUseCity(String useCity) {
        this.useCity = useCity;
    }

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }
}
