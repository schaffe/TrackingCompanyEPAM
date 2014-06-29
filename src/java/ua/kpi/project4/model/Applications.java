/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import ua.kpi.project4.Constants;

/**
 *
 * @author User
 */
public class Applications implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer applicationId;
    private UserAccounts creatorUserAccount;
    private String from;
    private String destination;
    private Date arrivalTime;
    private int passengersNum;
    private Constants.ApplicationStatus status;
    private Drivers driver;
    private Date dateCreate;

    public Applications() {
    }

    public Applications(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Applications(Integer applicationId, UserAccounts creatorUserAccount, 
            String from, String destination, Date arrivalTime, int passengersNum, 
            String status, Drivers driver, Date dateCreate) {
        this.applicationId = applicationId;
        this.creatorUserAccount = creatorUserAccount;
        this.from = from;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.passengersNum = passengersNum;
        this.status = Constants.ApplicationStatus.valueOf(status);
        this.driver = driver;
        this.dateCreate = dateCreate;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPassengersNum() {
        return passengersNum;
    }

    public void setPassengersNum(int passengersNum) {
        this.passengersNum = passengersNum;
    }

    public UserAccounts getCreatorUserAccount() {
        return creatorUserAccount;
    }

    public void setCreatorUserAccount(UserAccounts creatorUserAccount) {
        this.creatorUserAccount = creatorUserAccount;
    }

    public Drivers getDriver() {
        return driver;
    }

    public void setDriver(Drivers driver) {
        this.driver = driver;
    }


    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Constants.ApplicationStatus.valueOf(status);
    }


    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applications)) {
            return false;
        }
        Applications other = (Applications) object;
        if ((this.applicationId == null && other.applicationId != null) || 
                (this.applicationId != null && !this.applicationId.equals(other.applicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.kpi.project4.model.Applications[ applicationId=" + applicationId + " ]";
    }

}
