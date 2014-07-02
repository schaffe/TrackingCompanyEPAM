package ua.kpi.project4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ua.kpi.project4.Constants;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.Drivers;
import ua.kpi.project4.model.UserAccounts;

/**
 * Authorization or authorization is the function of specifying access rights to
 * resources related to information security and computer security in general
 * and to access control in particular. More formally, "to authorize" is to
 * define an access policy. For example, human resources staff is normally
 * authorized to access employee records and this policy is usually formalized
 * as access control rules in a computer system.
 */
public class ActionAuth implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();
        String login = request.getParameter(RequestParameters.LOGIN);
        String password = request.getParameter(RequestParameters.PASSWORD);

        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory();
            UserAccounts account = daoFactory.getUserAccountsDAO(daoFactory.getConnection()).getByLogin(login);
            if (password.equals(account.getPassword())) {
                int userId = account.getUserAccountId();
                String name = account.getFullName();
                HttpSession session = request.getSession(true);
                session.setAttribute(RequestParameters.FULLNAME, name);
                session.setAttribute(SessionParameters.USER_ID, userId);
                session.setAttribute(SessionParameters.PROFILE_ID, account.getProfile());

                if (account.getProfile().equals(Profiles.DRIVER.name())) {
                    Drivers driver = daoFactory.getDriversDao(daoFactory.getConnection()).getByUserAccount(account.getUserAccountId());
                    session.setAttribute(SessionParameters.DRIVER_ID, driver.getDriverId());
                }
                return Pages.WELCOME_PAGE;
            }
        } catch (IllegalArgumentException e) {
        }

        return Pages.INDEX;
    }
}
