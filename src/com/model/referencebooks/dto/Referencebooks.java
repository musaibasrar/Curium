package com.model.referencebooks.dto;

// default package
// Generated 10 Aug, 2018 1:37:19 PM by Hibernate Tools 5.3.0.Beta2

/**
 * Referencebooks generated by hbm2java
 */
public class Referencebooks implements java.io.Serializable {

    private Integer idreferencebooks;
    private String examlevelcode;
    private String referencebooks;
    private String language;

    public Referencebooks() {}

    public Referencebooks(String examlevelcode, String referencebooks, String language) {
        this.examlevelcode = examlevelcode;
        this.referencebooks = referencebooks;
        this.language = language;
    }

    public Integer getIdreferencebooks() {
        return this.idreferencebooks;
    }

    public void setIdreferencebooks(Integer idreferencebooks) {
        this.idreferencebooks = idreferencebooks;
    }

    public String getExamlevelcode() {
        return this.examlevelcode;
    }

    public void setExamlevelcode(String examlevelcode) {
        this.examlevelcode = examlevelcode;
    }

    public String getReferencebooks() {
        return this.referencebooks;
    }

    public void setReferencebooks(String referencebooks) {
        this.referencebooks = referencebooks;
    }

    
    public String getLanguage() {
        return this.language;
    }

    
    public void setLanguage(String language) {
        this.language = language;
    }

}
