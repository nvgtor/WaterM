package com.water.nvgtor.watermanegement.bean;

import java.util.Date;

/**
 * Created by dell on 2015/8/21.
 */
public class PatrolRows {
    private String remark;
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;
    private int isDeleted;
    private String id;
    private String planName;
    private String planID;
    private String personName;
    private String personID;
    private Date startTime;
    private Date endTime;
    private double timeLimit;
    private String dispatchingPerson;
    private Date dispatchTime;
    private int status;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getDispatchingPerson() {
        return dispatchingPerson;
    }

    public void setDispatchingPerson(String dispatchingPerson) {
        this.dispatchingPerson = dispatchingPerson;
    }

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PatrolRows{" +
                "remark='" + remark + '\'' +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", updateName='" + updateName + '\'' +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                ", id='" + id + '\'' +
                ", planName='" + planName + '\'' +
                ", planID='" + planID + '\'' +
                ", personName='" + personName + '\'' +
                ", personID='" + personID + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", timeLimit=" + timeLimit +
                ", dispatchingPerson='" + dispatchingPerson + '\'' +
                ", dispatchTime=" + dispatchTime +
                ", status=" + status +
                '}';
    }
}
