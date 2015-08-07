package com.water.nvgtor.watermanegement.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/7/31.
 */
public class MapPointInfo implements Serializable {


    private static final long serialVersionUID = -8752472955522131171L;

    private String deviceID;
    private String deviceName;
    private String deviceType;
    private String deviceRepair;
    private double deviceLongitude;
    private double deviceLatitude;
    private String deviceProject;
    private String deviceAddr;
    private String deviceRemark;

    public MapPointInfo(String deviceID, String deviceName, String deviceType, String deviceRepair,
                        double deviceLongitude, double deviceLatitude, String deviceProject,
                        String deviceAddr, String deviceRemark) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.deviceRepair = deviceRepair;
        this.deviceLongitude = deviceLongitude;
        this.deviceLatitude = deviceLatitude;
        this.deviceProject = deviceProject;
        this.deviceAddr = deviceAddr;
        this.deviceRemark = deviceRemark;
    }

    public static List<MapPointInfo> infos = new ArrayList<MapPointInfo>();

    static
    {
        infos.add(new MapPointInfo("001","水表1","水表","表针",117.53204,39.13829,"911",
                "天津滨海新区渤龙湖公园","我坏了，来修理我"));
        infos.add(new MapPointInfo("002","水表2","水表","表针",117.540402,39.135121,"911",
                "天津滨海新区渤龙湖公园","我坏了，来修理我"));
        infos.add(new MapPointInfo("003","水表3","水表","表针",117.525873,39.134288,"911",
                "天津滨海新区渤龙湖公园","我坏了，来修理我"));
        infos.add(new MapPointInfo("003","水表4","水表","表针",117.540551,39.126703,"911",
            "天津滨海新区渤龙湖公园","我坏了，来修理我"));
        infos.add(new MapPointInfo("005","水表5","水表","表针",117.539915,39.129747,"911",
                "天津滨海新区渤龙湖公园","我坏了，来修理我"));
        infos.add(new MapPointInfo("006","水表6","水表","表针",117.533359,39.125493,"911",
                "天津滨海新区渤龙湖公园","我坏了，来修理我"));
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceRepair() {
        return deviceRepair;
    }

    public void setDeviceRepair(String deviceRepair) {
        this.deviceRepair = deviceRepair;
    }

    public double getDeviceLongitude() {
        return deviceLongitude;
    }

    public void setDeviceLongitude(double deviceLongitude) {
        this.deviceLongitude = deviceLongitude;
    }

    public double getDeviceLatitude() {
        return deviceLatitude;
    }

    public void setDeviceLatitude(double deviceLatitude) {
        this.deviceLatitude = deviceLatitude;
    }

    public String getDeviceProject() {
        return deviceProject;
    }

    public void setDeviceProject(String deviceProject) {
        this.deviceProject = deviceProject;
    }

    public String getDeviceAddr() {
        return deviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        this.deviceAddr = deviceAddr;
    }

    public String getDeviceRemark() {
        return deviceRemark;
    }

    public void setDeviceRemark(String deviceRemark) {
        this.deviceRemark = deviceRemark;
    }
}
