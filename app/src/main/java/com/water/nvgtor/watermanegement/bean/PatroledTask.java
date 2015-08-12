package com.water.nvgtor.watermanegement.bean;

/**
 * Created by dell on 2015/8/11.
 */
public class PatroledTask {
    private String taskName;
    private String taskArea;
    private String executeMan;
    private String deadline;
    private int finalState;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskArea() {
        return taskArea;
    }

    public void setTaskArea(String taskArea) {
        this.taskArea = taskArea;
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

    public int getFinalState() {
        return finalState;
    }

    public void setFinalState(int finalState) {
        this.finalState = finalState;
    }
}
