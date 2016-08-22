package com.fat246.orders.bean;

/**
 * Created by hx on 2016/8/22.
 */
public class TimeStandInfo {
    private String tCreate;
    private String tSerive;
    private String tCommit;
    private String tApprove;
    private String tFinish;

    public TimeStandInfo(String tCreate, String tSerive,
                         String tApprove, String tCommit, String tFinish) {
        this.tCreate = tCreate;
        this.tSerive = tSerive;
        this.tApprove = tApprove;
        this.tCommit = tCommit;
        this.tFinish = tFinish;
    }


    public String gettCreate() {
        return tCreate;
    }


    public String gettFinish() {
        return tFinish;
    }


    public String gettApprove() {
        return tApprove;
    }


    public String gettCommit() {
        return tCommit;
    }


    public String gettSerive() {
        return tSerive;
    }


}
