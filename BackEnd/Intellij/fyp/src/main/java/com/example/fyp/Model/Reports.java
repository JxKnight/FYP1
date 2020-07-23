package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="Reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "reports_id",unique = true)
    private String reportsId;
    @Column(name = "reports_description")
    private String reportsDescription;
    @Column(name = "reports_name")
    private String reportsName;
    @Column(name = "reports_file")
    private String reportsFile;
    @Column(name = "reports_date")
    private String reportsDate;
    @Column(name = "reports_date2date")
    private String reportsDate2Date;
    @Column(name = "reports_type")
    private String reportsType;

    public String getReportsId() {
        return reportsId;
    }

    public void setReportsId(String reportsId) {
        this.reportsId = reportsId;
    }

    public String getReportsDescription() {
        return reportsDescription;
    }

    public void setReportsDescription(String reportsDescription) {
        this.reportsDescription = reportsDescription;
    }

    public String getReportsName() {
        return reportsName;
    }

    public void setReportsName(String reportsName) {
        this.reportsName = reportsName;
    }

    public String getReportsFile() {
        return reportsFile;
    }

    public void setReportsFile(String reportsFile) {
        this.reportsFile = reportsFile;
    }

    public String getReportsDate() {
        return reportsDate;
    }

    public void setReportsDate(String reportsDate) {
        this.reportsDate = reportsDate;
    }

    public String getReportsDate2Date() {
        return reportsDate2Date;
    }

    public void setReportsDate2Date(String reportsDate2Date) {
        this.reportsDate2Date = reportsDate2Date;
    }

    public String getReportsType() {
        return reportsType;
    }

    public void setReportsType(String reportsType) {
        this.reportsType = reportsType;
    }
}
