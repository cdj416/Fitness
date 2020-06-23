package com.hongyuan.fitness.ui.shop.sactivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityMapBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.MapViewModel;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.MapUtil;

public class MapActivity extends CustomActivity {

    private AcitivityMapBinding binding;

    //初始化地图控制器对象
    private AMap aMap;
    //定位样式
    private MyLocationStyle myLocationStyle;

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_map;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.color.transparent,"");

        binding = AcitivityMapBinding.bind(mView);
        MapViewModel viewModel = new MapViewModel(this,binding);
        binding.setViewModel(viewModel);

        binding.goBack.setOnClickListener(v -> {
            finish();
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.title.setText(getBundle().getString("os_name"));
        binding.content.setText(getBundle().getString("address"));


        // 调用地图所必须重写
        binding.mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = binding.mapView.getMap();
        }
        setMyLocationStyle();

    }

    /*
     * 初始化样式
     * */
    private void setMyLocationStyle(){
        String latitude = getBundle().getString("latitude");
        String longitude = getBundle().getString("longitude");

        CameraPosition cameraPosition = new CameraPosition(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)), 15, 0, 30);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        aMap.moveCamera(cameraUpdate);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.blue_loacation))
                .draggable(true);
        Marker marker = aMap.addMarker(markerOptions);
        marker.showInfoWindow();

        binding.goMap.setOnClickListener(v -> {
            if (MapUtil.checkMapAppsIsExist(MapActivity.this,MapUtil.GAODE_PKG)){
                CustomDialog.promptDialog(this, "是否打开高德地图进行导航？", "确定", "取消", false, new CustomDialog.DialogClick() {
                    @Override
                    public void dialogClick(View v) {
                        if(v.getId() == R.id.isOk){
                            MapUtil.openGaoDe(MapActivity.this ,markerOptions.getPosition().latitude,markerOptions.getPosition().longitude);
                        }
                    }
                });
            }else if (MapUtil.checkMapAppsIsExist(MapActivity.this , MapUtil.BAIDU_PKG)) {
                CustomDialog.promptDialog(this, "是否打开百度地图进行导航？", "确定", "取消", false, new CustomDialog.DialogClick() {
                    @Override
                    public void dialogClick(View v) {
                        if(v.getId() == R.id.isOk){
                            MapUtil.openBaidu(MapActivity.this ,markerOptions);
                        }
                    }
                });
            }else {
                CustomDialog.showMessage(this,"请到应用市场下载地图APP");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        binding.mapView.onSaveInstanceState(outState);
    }

}
