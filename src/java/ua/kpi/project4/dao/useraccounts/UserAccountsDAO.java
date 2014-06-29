/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kpi.project4.dao.useraccounts;

import ua.kpi.project4.model.UserAccounts;

/**
 *
 * @author User
 */
public interface UserAccountsDAO {
    UserAccounts getByLogin(String login);
    void update(UserAccounts account);
}
