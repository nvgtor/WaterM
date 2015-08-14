package com.water.nvgtor.watermanegement.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.water.nvgtor.watermanegement.adapter.RecorderAdapter;
import com.water.nvgtor.watermanegement.bean.MapPointInfo;
import com.water.nvgtor.watermanegement.bean.Recorder;
import com.water.nvgtor.watermanegement.tool.MyOrientationListener;
import com.water.nvgtor.watermanegement.view.AudioRecorderButton;
import com.water.nvgtor.watermanegement.view.MyMediaManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    //录音相关
    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas = new ArrayList<Recorder>();
    private AudioRecorderButton mAudioRecorderButton;
    private View animView;

    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private boolean[] imgState = {false,false,false,false};
    //拍照
    private Button btnCamera;
    private Button btnGallery;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.patrol_map_detail);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle("返回 ");
        }

        this.context = this;

        initView();
        //初始化定位
        initLocation();

        initMarker();

        //拍照事件监听
        takePhoto();


        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle extraInfo = marker.getExtraInfo();
                MapPointInfo info = (MapPointInfo) extraInfo.getSerializable("info");
                TextView tv_ID = (TextView) findViewById(R.id.id_map_device_ID_in);
                TextView tv_name = (TextView) findViewById(R.id.id_map_device_name_in);
                TextView tv_type = (TextView) findViewById(R.id.id_map_device_type_in);
                TextView tv_repair = (TextView) findViewById(R.id.id_map_device_repair_in);
                TextView tv_longitude = (TextView) findViewById(R.id.id_map_device_longitude_in);
                TextView tv_latitude = (TextView) findViewById(R.id.id_map_device_latitude_in);
                TextView tv_project = (TextView) findViewById(R.id.id_map_device_project_in);
                TextView tv_addr = (TextView) findViewById(R.id.id_map_device_address_in);
                TextView tv_remark = (TextView) findViewById(R.id.id_map_device_remark_in);

                tv_ID.setText(info.getDeviceID());
                tv_name.setText(info.getDeviceName());
                tv_type.setText(info.getDeviceType());
                tv_repair.setText(info.getDeviceRepair());
                tv_longitude.setText(info.getDeviceLongitude() + "");
                tv_latitude.setText(info.getDeviceLatitude() + "");
                tv_project.setText(info.getDeviceProject());
                tv_addr.setText(info.getDeviceAddr());
                tv_remark.setText(info.getDeviceRemark());


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

        mMarkerLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });


        mListView = (ListView) findViewById(R.id.id_device_recorder_list);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_map_device_microphone);
        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                Recorder recorder = new Recorder(seconds, filePath);
                mDatas.add(recorder);
                mAdapter.notifyDataSetChanged();//通知更新的内容
                mListView.setSelection(mDatas.size() - 1);//将lisview设置为最后一个
            }
        });

        mAdapter = new RecorderAdapter(this,mDatas);
        mListView.setAdapter(mAdapter);

        //listView的item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (animView != null) {
                    animView.setBackgroundResource(R.drawable.adj);
                    animView = null;
                }
                //播放动画
                animView = view.findViewById(R.id.recorder_anim);
                animView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable anim = (AnimationDrawable) animView.getBackground();
                anim.start();
                //播放音频
                MyMediaManager.playSound(mDatas.get(position).filePath, new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        animView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }
        });

    }

    private void initMarker()
    {
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.track_map_icon);
        mMarkerLy = (RelativeLayout) findViewById(R.id.id_map_marker_detail);
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

        btnCamera = (Button)findViewById(R.id.id_map_device_camera);
        btnGallery = (Button) findViewById(R.id.id_map_device_gallery);
        img1 = (ImageView) findViewById(R.id.id_map_img_add_1);
        img2 = (ImageView) findViewById(R.id.id_map_img_add_2);
        img3 = (ImageView) findViewById(R.id.id_map_img_add_3);
        img4 = (ImageView) findViewById(R.id.id_map_img_add_4);

    }

    private void takePhoto(){
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnCamera.setBackgroundResource(R.drawable.btn_posts_camera_l_1);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnGallery.setBackgroundResource(R.drawable.btn_posts_photo_l_1);
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(intent, "Select File"),
                        SELECT_FILE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!imgState[0]){
            img1.setImageBitmap(thumbnail);
            imgState[0] = true;
        }else if (!imgState[1]){
            img2.setImageBitmap(thumbnail);
            imgState[1] = true;
        }else if (!imgState[2]){
            img3.setImageBitmap(thumbnail);
            imgState[2] = true;
        }else if (!imgState[3]){
            img4.setImageBitmap(thumbnail);
            imgState[3] = true;
        }else{
            Toast.makeText(PatrolMapDetailActivity.this, "照片已满",Toast.LENGTH_SHORT).show();
        }


    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);

        if (!imgState[0]){
            img1.setImageBitmap(bm);
            imgState[0] = true;
        }else if (!imgState[1]){
            img2.setImageBitmap(bm);
            imgState[1] = true;
        }else if (!imgState[2]){
            img3.setImageBitmap(bm);
            imgState[2] = true;
        }else if (!imgState[3]){
            img4.setImageBitmap(bm);
            imgState[3] = true;
        }else{
            Toast.makeText(PatrolMapDetailActivity.this, "照片已满",Toast.LENGTH_SHORT).show();
        }
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
            latLng = new LatLng(info.getDeviceLatitude(), info.getDeviceLongitude());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
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
}
