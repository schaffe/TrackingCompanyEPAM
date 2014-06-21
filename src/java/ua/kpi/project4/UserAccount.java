package ua.kpi.project4;

/**
 * UserAccount class.
 * 
 * @author Artur Dzidzoiev
 * @version May 28, 2014
 */
public class UserAccount {
    private int userAccountId;
    private String fullname;
    private String login;
    private String password;

    public UserAccount(int userAccountId, String fullname, String login, String password) {
        this.userAccountId = userAccountId;
        this.fullname = fullname;
        this.login = login;
        this.password = password;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
    
    
}
