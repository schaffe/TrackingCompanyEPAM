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
public class UserAccounts implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userAccountId;
    private String fullName;
    private String login;
    private String password;
    private int profileId;

    public UserAccounts() {
    }

    public UserAccounts(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public UserAccounts(Integer userAccountId, String login, String password) {
        this.userAccountId = userAccountId;
        this.login = login;
        this.password = password;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAccountId != null ? userAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAccounts)) {
            return false;
        }
        UserAccounts other = (UserAccounts) object;
        if ((this.userAccountId == null && other.userAccountId != null) || (this.userAccountId != null && !this.userAccountId.equals(other.userAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.kpi.project4.model.UserAccounts[ userAccountId=" + userAccountId + " ]";
    }

}
