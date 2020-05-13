package com.hongyuan.fitness.custom_view.time_selecter.get_address;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.AssetManager;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.reflect.TypeToken;
import com.hongyuan.fitness.util.GsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.hongyuan.fitness.ui.shop.sactivity.CheckInShopActivity.ACTION_TYPE_THREAD;

public class GetAddressDataService extends IntentService {

    private List<JsonFileBean.DataBean> provinceList = new ArrayList<>();
    private List<List<JsonFileBean.DataBean.ChildBeanX>> mCityList = new ArrayList<>();
    private List<List<List<JsonFileBean.DataBean.ChildBeanX.ChildBean>>> mAreaList = new ArrayList<>();

    private LocalBroadcastManager mLocalBroadcastManager;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public GetAddressDataService() {
        super("GetAddressDataService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        JsonFileBean addressData;
        try {
            addressData = GsonUtil.getGson().fromJson(getJson("region.json"), new TypeToken<JsonFileBean>(){}.getType());
            sendThreadStatus(assembleData(addressData));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    * 组装使用的省市区数据
    * */
    private JsonDataBean assembleData(JsonFileBean addressData){
        //使用的数据
        JsonDataBean useBeans = new JsonDataBean();

        //升级数据
        provinceList = addressData.getData();

        for (int i = 0; i < provinceList.size(); i++) {//遍历省份
            List<JsonFileBean.DataBean.ChildBeanX> cityList = new ArrayList<>();//该省的城市列表（第二级）
            List<List<JsonFileBean.DataBean.ChildBeanX.ChildBean>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < provinceList.get(i).getChild().size(); c++) {//遍历该省份的所有城市
                cityList.add(provinceList.get(i).getChild().get(c));//添加城市

                List<JsonFileBean.DataBean.ChildBeanX.ChildBean> city_AreaList = new ArrayList<>();
                city_AreaList.addAll(provinceList.get(i).getChild().get(c).getChild());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            //添加市集数据
            mCityList.add(cityList);

            //添加区级数据
            mAreaList.add(province_AreaList);
        }

        useBeans.setProvinceList(provinceList);
        useBeans.setCityList(mCityList);
        useBeans.setAreaList(mAreaList);

        return useBeans;
    }

    /**
     * 发送数据
     */
    private void sendThreadStatus(JsonDataBean addressData) {
        Intent intent = new Intent(ACTION_TYPE_THREAD);
        intent.putExtra("address",addressData);
        mLocalBroadcastManager.sendBroadcast(intent);
    }


    /*
    * 读取json文件转换成字符串
    * */
    public String getJson(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager =  getApplicationContext().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
