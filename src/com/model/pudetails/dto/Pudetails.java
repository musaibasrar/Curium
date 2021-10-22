package com.model.pudetails.dto;

// default package
// Generated 20 Jun, 2018 1:03:58 PM by Hibernate Tools 5.3.0.Beta2

/**
 * Pudetails generated by hbm2java
 */
public class Pudetails implements java.io.Serializable {

    private Integer idpudetails;
    private Integer exampassedappearance;
    private String exampassedyear;
    private String exampassedregno;
    private String exampassedresultwithclass;
    private String secondlanguage;
    private String aggregatemarkssslc;
    private String optionalsubjects;
    private String compulsorysubjects;
    private String sslcmediuminstruction;
    private String pumediuminstruction;
    private int userid;
    
    public Pudetails() {}

    public Pudetails(Integer exampassedappearance, String exampassedyear, String exampassedregno, String exampassedresultwithclass,
            String secondlanguage, String aggregatemarkssslc, String optionalsubjects, String compulsorysubjects,
            String sslcmediuminstruction, String pumediuminstruction, int userid) {
        this.exampassedappearance = exampassedappearance;
        this.exampassedyear = exampassedyear;
        this.exampassedregno = exampassedregno;
        this.exampassedresultwithclass = exampassedresultwithclass;
        this.secondlanguage = secondlanguage;
        this.aggregatemarkssslc = aggregatemarkssslc;
        this.optionalsubjects = optionalsubjects;
        this.compulsorysubjects = compulsorysubjects;
        this.sslcmediuminstruction = sslcmediuminstruction;
        this.pumediuminstruction = pumediuminstruction;
        this.userid = userid;
    }

    public Integer getIdpudetails() {
        return this.idpudetails;
    }

    public void setIdpudetails(Integer idpudetails) {
        this.idpudetails = idpudetails;
    }

    public Integer getExampassedappearance() {
        return this.exampassedappearance;
    }

    public void setExampassedappearance(Integer exampassedappearance) {
        this.exampassedappearance = exampassedappearance;
    }

    public String getExampassedyear() {
        return this.exampassedyear;
    }

    public void setExampassedyear(String exampassedyear) {
        this.exampassedyear = exampassedyear;
    }

    public String getExampassedregno() {
        return this.exampassedregno;
    }

    public void setExampassedregno(String exampassedregno) {
        this.exampassedregno = exampassedregno;
    }

    public String getExampassedresultwithclass() {
        return this.exampassedresultwithclass;
    }

    public void setExampassedresultwithclass(String exampassedresultwithclass) {
        this.exampassedresultwithclass = exampassedresultwithclass;
    }

    public String getSecondlanguage() {
        return this.secondlanguage;
    }

    public void setSecondlanguage(String secondlanguage) {
        this.secondlanguage = secondlanguage;
    }

    public String getAggregatemarkssslc() {
        return this.aggregatemarkssslc;
    }

    public void setAggregatemarkssslc(String aggregatemarkssslc) {
        this.aggregatemarkssslc = aggregatemarkssslc;
    }

    public String getOptionalsubjects() {
        return this.optionalsubjects;
    }

    public void setOptionalsubjects(String optionalsubjects) {
        this.optionalsubjects = optionalsubjects;
    }

    public String getCompulsorysubjects() {
        return this.compulsorysubjects;
    }

    public void setCompulsorysubjects(String compulsorysubjects) {
        this.compulsorysubjects = compulsorysubjects;
    }

    public String getSslcmediuminstruction() {
        return this.sslcmediuminstruction;
    }

    public void setSslcmediuminstruction(String sslcmediuminstruction) {
        this.sslcmediuminstruction = sslcmediuminstruction;
    }

    public String getPumediuminstruction() {
        return this.pumediuminstruction;
    }

    public void setPumediuminstruction(String pumediuminstruction) {
        this.pumediuminstruction = pumediuminstruction;
    }

    public int getUserid() {
    		return userid;
    }

    public void setUserid(int userid) {
    	this.userid = userid;
    }
}
