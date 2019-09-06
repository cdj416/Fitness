package com.hongyuan.fitness.ui.out_door.gaodei_map;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.MyLocationUtil;

import java.util.ArrayList;
import java.util.List;

public class MyMapActivity extends AppCompatActivity implements MyLocationUtil.GetLocation {
    private MapView mapView;
    //初始化地图控制器对象
    private AMap aMap;
    //定位样式
    private MyLocationStyle myLocationStyle;

    //定位信息
    private AMapLocation oldLocation;
    //存储坐标信息点
    private List<LatLngPoint> latLngPoints = new ArrayList<>();
    //定位工具类
    private MyLocationUtil locationUtil;
    //下标
    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapView = findViewById(R.id.mapView);
        // 调用地图所必须重写
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        setMyLocationStyle();
    }


    /*
    * 初始化样式
    * */
    private void setMyLocationStyle(){
        myLocationStyle = new MyLocationStyle();
        // 设置圆形的边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //设置放大倍数
        aMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        //设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);

        locationUtil =  MyLocationUtil.getInstance();
        //设置定位回调信息
        locationUtil.setGetLocation(this);
        //开始定位
        locationUtil.startLocation();
    }



    /**
     * 绘制运动路线
     *
     * @param curLocation
     */
    public void drawLines(AMapLocation curLocation) {

        if (null == oldLocation) {
            return;
        }
        if (curLocation.getLatitude() != 0.0 && curLocation.getLongitude() != 0.0
                && oldLocation.getLongitude() != 0.0 && oldLocation.getLatitude() != 0.0) {
            PolylineOptions options = new PolylineOptions();
            //上一个点的经纬度
            options.add(new LatLng(oldLocation.getLatitude(), oldLocation.getLongitude()));
            //当前的经纬度
            options.add(new LatLng(curLocation.getLatitude(), curLocation.getLongitude()));
            options.width(10).geodesic(true).color(R.color.color_EF5B48);
            aMap.addPolyline(options);
        }

    }

    /*
    * 两点间距离计算
    * */
    private double Distance(AMapLocation curLocation) {

        Log.e("cdj","===============curLocation========"+curLocation.getLatitude()+"==============oldLocation=========="+oldLocation.getLatitude());
        double distance;
        distance = AMapUtils.calculateLineDistance(new LatLng(oldLocation.getLatitude(),
                oldLocation.getLongitude()), new LatLng(curLocation.getLatitude(),
                curLocation.getLongitude()));
        distance += distance;
        return distance;
    }

    /*
    * 定位信息回调
    * */
    @Override
    public void changeData(AMapLocation location) {
        //先去划线
        drawLines(location);
        //记录
        oldLocation = location;
        //将坐标点存于数组里
        if (location.getLatitude() != 0 && location.getLongitude() != 0) {
            latLngPoints.add(new LatLngPoint(index++, new LatLng(location.getLatitude(), location.getLongitude())));
        }
        Log.e("cdj","=======距离=========="+location.getSpeed());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationUtil.stopLocation();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }


}
