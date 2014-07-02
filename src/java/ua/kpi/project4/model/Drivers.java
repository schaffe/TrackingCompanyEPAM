/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.model;

import java.io.Serializable;

/**
 * Entity model of table Drivers.
 */
public class Drivers implements Serializable {

    private static final long serialVersionUID = 548847L;
    private Integer driverId;
    private UserAccounts userAccount;
    private Cars car;

    public Drivers() {
    }

    public Drivers(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Drivers(Integer driverId, UserAccounts userAccount, Cars car) {
        this.driverId = driverId;
        this.userAccount = userAccount;
        this.car = car;
    }

    public UserAccounts getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccounts userAccount) {
        this.userAccount = userAccount;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (driverId != null ? driverId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drivers)) {
            return false;
        }
        Drivers other = (Drivers) object;
        if ((this.driverId == null && other.driverId != null) || (this.driverId != null && !this.driverId.equals(other.driverId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.kpi.project4.model.Drivers[ driverId=" + driverId + " ]";
    }

}
