package com.water.nvgtor.watermanegement.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/7/31.
 */
public class MapPointInfo implements Serializable {


    private static final long serialVersionUID = -8752472955522131171L;

    private double latitude;
    private double longitude;
    private int id;
    private String name;
    private String deviceInfo;

    public MapPointInfo(double latitude, double longitude, int id, String name, String deviceInfo) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
        this.name = name;
        this.deviceInfo = deviceInfo;
    }

    public static List<MapPointInfo> infos = new ArrayList<MapPointInfo>();

    static
    {
        infos.add(new MapPointInfo(39.13829,117.53204,1,"巡检点1","我是设备信息"));
        infos.add(new MapPointInfo(39.135121, 117.540402,2,"巡检点2","我是设备信息"));
        infos.add(new MapPointInfo(39.134288, 117.525873,3,"巡检点3","我是设备信息"));
        infos.add(new MapPointInfo(39.126703, 117.540551,5,"巡检点3","我是设备信息"));
        infos.add(new MapPointInfo(39.129747, 117.539915,4,"巡检点4","我是设备信息"));
        infos.add(new MapPointInfo(39.125493, 117.533359,6,"巡检点4","我是设备信息"));

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
