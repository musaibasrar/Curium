package org.ideoholic.curium.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SMSReportResponse {
    private String responseCode;
    private String totalsize;
    private List<SMSReport> records;

    // Getters and Setters
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(String totalsize) {
        this.totalsize = totalsize;
    }

    public List<SMSReport> getRecords() {
        return records;
    }

    public void setRecords(List<SMSReport> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "SMSReportResponse{" +
                "responseCode='" + responseCode + '\'' +
                ", totalsize='" + totalsize + '\'' +
                ", records=" + records +
                '}';
    }
}
