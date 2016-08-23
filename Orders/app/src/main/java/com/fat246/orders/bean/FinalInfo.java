package com.fat246.orders.bean;

/**
 * Created by hx on 2016/8/23.
 */
public class FinalInfo {

    public static final String prhsord_id = "prhsord_id";
    public static final String is_passed = "is_passed";

    private boolean IS_PASSED;



    private String fCode;
    private String fName;
    private String fOrderq;
    private String fReceiveq;
    private String fStorageq;
    private String fReturnq;


    public FinalInfo(String fReturnq, String fStorageq,
                     String fReceiveq, String fOrderq, String fName, String fCode) {
        this.fReturnq = fReturnq;
        this.fStorageq = fStorageq;
        this.fReceiveq = fReceiveq;
        this.fOrderq = fOrderq;
        this.fName = fName;
        this.fCode = fCode;
    }

    private FinalInfo(String fCode){
        this.fCode=fCode;
        fName="";
        fOrderq="";
        fReceiveq="";
        fReturnq="";
        fStorageq="";
        IS_PASSED=false;
    }

    public boolean IS_PASSED() {
        return IS_PASSED;
    }
}
