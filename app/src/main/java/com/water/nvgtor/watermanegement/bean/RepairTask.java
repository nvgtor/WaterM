package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/21.
 */
public class RepairTask {
    private String taskDes;
    private String taskAddr;
    private String executeMan;
    private String deadline;

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
    }

    public String getTaskAddr() {
        return taskAddr;
    }

    public void setTaskAddr(String taskAddr) {
        this.taskAddr = taskAddr;
    }

    public String getExecuteMan() {
        return executeMan;
    }

    public void setExecuteMan(String executeMan) {
        this.executeMan = executeMan;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
