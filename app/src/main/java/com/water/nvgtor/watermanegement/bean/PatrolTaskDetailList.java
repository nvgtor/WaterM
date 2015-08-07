package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/6.
 */
public class PatrolTaskDetailList {
    private String patrolPointID;
    private String patrolPointName;
    private String patrolPointAddress;
    private int deviceNum;

    public String getPatrolPointID() {
        return patrolPointID;
    }

    public void setPatrolPointID(String patrolPointID) {
        this.patrolPointID = patrolPointID;
    }

    public String getPatrolPointName() {
        return patrolPointName;
    }

    public void setPatrolPointName(String patrolPointName) {
        this.patrolPointName = patrolPointName;
    }

    public String getPatrolPointAddress() {
        return patrolPointAddress;
    }

    public void setPatrolPointAddress(String patrolPointAddress) {
        this.patrolPointAddress = patrolPointAddress;
    }

    public int getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(int deviceNum) {
        this.deviceNum = deviceNum;
    }
}
