/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kpi.project4.model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Voyage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer voyageId;
    private Boolean isCompleted;
    private Applications applications;
    private Drivers drivers;

    public Voyage() {
    }

    public Voyage(Integer voyageId) {
        this.voyageId = voyageId;
    }

    public Integer getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(Integer voyageId) {
        this.voyageId = voyageId;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Applications getApplications() {
        return applications;
    }

    public void setApplications(Applications applications) {
        this.applications = applications;
    }

    public Drivers getDrivers() {
        return drivers;
    }

    public void setDrivers(Drivers drivers) {
        this.drivers = drivers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voyageId != null ? voyageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voyage)) {
            return false;
        }
        Voyage other = (Voyage) object;
        if ((this.voyageId == null && other.voyageId != null) || (this.voyageId != null && !this.voyageId.equals(other.voyageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.kpi.project4.model.Voyage[ voyageId=" + voyageId + " ]";
    }
    
}
