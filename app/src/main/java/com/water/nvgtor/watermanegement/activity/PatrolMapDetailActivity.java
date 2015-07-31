package com.water.nvgtor.watermanegement.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.water.nvgtor.watermanegement.R;
import com.water.nvgtor.watermanegement.bean.MapPointInfo;
import com.water.nvgtor.watermanegement.tool.MyOrientationListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/7/30.
 */
public class PatrolMapDetailActivity extends Activity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    private Context context;
    private ArrayList<MapPointInfo> mMapList;

    // 定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private boolean isFirstIn = true;
    private double mLatitude;//最新经纬度
    private double mLongtitude;

    // 自定义定位图标
    private BitmapDescriptor mIconLocation;
    private MyOrientationListener myOrientationListener;
    private float mCurrentX;
    private MyLocationConfiguration.LocationMode mLocationMode;

    //覆盖物相关
    private BitmapDescriptor mMarker;
    private RelativeLayout mMarkerLy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.patrol_map_detail);

        this.context = this;

        initView();
        //初始化定位
        initLocation();

        initMarker();

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle extraInfo = marker.getExtraInfo();
                MapPointInfo info = (MapPointInfo) extraInfo.getSerializable("info");
                TextView tv_name = (TextView) findViewById(R.id.id_point_name);
                TextView tv_latitude = (TextView) findViewById(R.id.id_point_latitude);
                TextView tv_longitude = (TextView) findViewById(R.id.id_point_longitude);
                TextView tv_devInf = (TextView) findViewById(R.id.id_point_dev);

                tv_name.setText(info.getName());
                tv_latitude.setText(info.getLatitude() + "");
                tv_longitude.setText(info.getLongitude() + "");
                tv_devInf.setText(info.getDeviceInfo());

                mMarkerLy.setVisibility(View.VISIBLE);

                return true;
            }
        });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMarkerLy.setVisibility(View.GONE);
                mBaiduMap.hideInfoWindow();
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

    }

    private void initMarker()
    {
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.maker);
        mMarkerLy = (RelativeLayout) findViewById(R.id.id_marker_ly);
    }

    private void initLocation(){

        mLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
         mLocationClient = new LocationClient(this);
         mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
        //初始化图标
        mIconLocation = BitmapDescriptorFactory
                .fromResource(R.drawable.navi_map_gps_locked);

        myOrientationListener = new MyOrientationListener(context);

        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });


    }

    private void initView(){
        mMapView = (MapView)findViewById(R.id.id_bmapView);
        //设置地图放大比例
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 开启定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted())
            mLocationClient.start();

        // 开启方向传感器
        myOrientationListener.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 停止定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();

        // 停止方向传感器
        myOrientationListener.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.id_map_common:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;

            case R.id.id_map_site:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;

            case R.id.id_map_traffic:
                if (mBaiduMap.isTrafficEnabled())
                {
                    mBaiduMap.setTrafficEnabled(false);
                    item.setTitle("实时交通(off)");
                } else
                {
                    mBaiduMap.setTrafficEnabled(true);
                    item.setTitle("实时交通(on)");
                }
                break;
            case R.id.id_map_location:
                centerToMyLocation();
                break;
            case R.id.id_map_mode_common:
                mLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
                break;
            case R.id.id_map_mode_following:
                mLocationMode = MyLocationConfiguration.LocationMode.FOLLOWING;
                break;
            case R.id.id_map_mode_compass:
                mLocationMode = MyLocationConfiguration.LocationMode.COMPASS;
                break;
            case R.id.id_add_overlay:
                addOverlays(MapPointInfo.infos);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyLocationListener implements BDLocationListener {

        //参数较多时，建立build内部类初始化参数，.build建立初始化对象
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData data = new MyLocationData.Builder()//
            .direction(mCurrentX)//
            .accuracy(bdLocation.getRadius())//精度
            .latitude(bdLocation.getLatitude())//
            .longitude(bdLocation.getLongitude())
            .build();
            mBaiduMap.setMyLocationData(data);

            // 设置自定义图标
            MyLocationConfiguration config = new MyLocationConfiguration(
                    mLocationMode, true, mIconLocation);
            mBaiduMap.setMyLocationConfigeration(config);


            //更新最新经纬度
            mLatitude = bdLocation.getLatitude();
            mLongtitude = bdLocation.getLongitude();

            if (isFirstIn)
            {
                LatLng latLng = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(msu);
                isFirstIn = false;

                Toast.makeText(context, bdLocation.getAddrStr(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 定位到我的位置
     */
    private void centerToMyLocation()
    {
        LatLng latLng = new LatLng(mLatitude, mLongtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }



    /**
     * 添加覆盖物
     */
    private void addOverlays(List<MapPointInfo> infos)
    {
        mBaiduMap.clear();
        LatLng latLng = null;
        Marker marker = null;
        OverlayOptions options;
        for (MapPointInfo info : infos)
        {
            // 经纬度
            latLng = new LatLng(info.getLatitude(), info.getLongitude());
            // 图标
            options = new MarkerOptions().position(latLng).icon(mMarker)
                    .zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle arg0 = new Bundle();
            arg0.putSerializable("info", info);
            marker.setExtraInfo(arg0);
        }

        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(msu);

    }
}
