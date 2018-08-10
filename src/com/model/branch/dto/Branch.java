package com.model.branch.dto;

// default package
// Generated 17 Jul, 2018 11:38:25 AM by Hibernate Tools 5.3.0.Beta2

/**
 * Branch generated by hbm2java
 */
public class Branch implements java.io.Serializable {

    private Integer idbranch;
    private String centername;
    private String centercode;
    private String districtcode;

    public Branch() {}

    public Branch(String centername, String centercode, String districtcode) {
        this.centername = centername;
        this.centercode = centercode;
        this.districtcode = districtcode;
    }

    public Integer getIdbranch() {
        return this.idbranch;
    }

    public void setIdbranch(Integer idbranch) {
        this.idbranch = idbranch;
    }

    public String getCentername() {
        return this.centername;
    }

    
    public void setCentername(String centername) {
        this.centername = centername;
    }

    
    public String getCentercode() {
        return this.centercode;
    }

    
    public void setCentercode(String centercode) {
        this.centercode = centercode;
    }

    
    public String getDistrictcode() {
        return this.districtcode;
    }

    
    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

  

}
