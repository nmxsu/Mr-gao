package com.fat246.orders.bean;

/**
 * Created by hx on 2016/8/18.
 */
public class TimeInfo {

    public static final String prhsord_id = "prhsord_id";
    public static final String is_passed = "is_passed";


    private boolean IS_PASSED;
    // 采购时间信息
    private String tCreate;
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


   /* public TimeInfo()

    //只包含ID的OrderInfo
    public OrderInfo(String PRHSORD_ID) {

        this.PRHSORD_ID = PRHSORD_ID;
        this.MATE_CODE = "";
        this.PRHS_SOUR = "";
        this.DEP_CODE = "";
        this.IS_PASSED = false;
    }

    //只读
    public String getPRHSORD_ID() {
        return this.PRHSORD_ID;
    }

    public String getNAMEE() {
        return this.NAMEE;
    }

    public String getPRAC_NAME() {
        return this.PRAC_NAME;
    }

    public boolean getIS_PASSED() {

        return this.IS_PASSED;
    }*/
}
