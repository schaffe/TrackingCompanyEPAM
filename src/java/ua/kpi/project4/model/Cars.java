/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kpi.project4.model;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author User
 */
public class Cars implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer carId;
    
    private String registrationNumber;
    
    private int placesNumber;
    private Boolean isValid;

    public Cars() {
    }

    public Cars(Integer carId) {
        this.carId = carId;
    }

    public Cars(Integer carId, int placesNumber) {
        this.carId = carId;
        this.placesNumber = placesNumber;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cars)) {
            return false;
        }
        Cars other = (Cars) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.kpi.project4.model.Cars[ carId=" + carId + " ]";
    }
    
}