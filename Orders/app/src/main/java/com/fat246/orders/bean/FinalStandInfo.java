package com.fat246.orders.bean;

/**
 * Created by hx on 2016/8/23.
 */
public class FinalStandInfo {
    private String fCode;
    private String fName;
    private String fOrderq;
    private String fReceiveq;
    private String fStorageq;
    private String fReturnq;

    public FinalStandInfo(String fStorageq, String fReturnq,
                          String fCode, String fName, String fOrderq, String fReceiveq) {
        this.fStorageq = fStorageq;
        this.fReturnq = fReturnq;
        this.fCode = fCode;
        this.fName = fName;
        this.fOrderq = fOrderq;
        this.fReceiveq = fReceiveq;
    }

    public String getfCode() {
        return fCode;
    }

    public String getfName() {
        return fName;
    }

    public String getfOrderq() {
        return fOrderq;
    }

    public String getfReceiveq() {
        return fReceiveq;
    }

    public String getfStorageq() {
        return fStorageq;
    }

    public String getfReturnq() {
        return fReturnq;
    }
}
