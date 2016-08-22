package com.fat246.orders.bean;

/**
 * Created by hx on 2016/8/18.
 */
public class TimeInfo {

    public static final String prhsord_id = "prhsord_id";
    public static final String is_passed = "is_passed";


    //订单ID
    private String PRHSORD_ID;

    //NAMEE
    private String NAMEE;

    //状态
    private String PRAC_NAME;

    private boolean IS_PASSED;

    // 采购时间信息
    private String tCreate;//private String PRHSORD_ID;
    private String tSerive;
    private String tCommit;
    private String tApprove;
    private String tFinish;

    public TimeInfo(boolean IS_PASSED, String tCreate, String tSerive, String tApprove,
                    String tCommit, String tFinish) {
        this.IS_PASSED = IS_PASSED;
        this.tCreate = tCreate;
        this.tSerive = tSerive;
        this.tApprove = tApprove;
        this.tCommit = tCommit;
        this.tFinish = tFinish;
    }


    public TimeInfo(String tCreate) {
        this.tCreate = tCreate;
        this.tSerive = "";
        this.tApprove = "";
        this.tCommit = "";
        this.tFinish = "";
        this.IS_PASSED = false;
    }





    public String getPRHSORD_ID() {
        return this.PRHSORD_ID;
    }//订单

    public String getNAMEE() {
        return this.NAMEE;
    }

    public String getPRAC_NAME() {
        return this.PRAC_NAME;
    }

    public boolean getIS_PASSED() {

        return this.IS_PASSED;
    }
}
