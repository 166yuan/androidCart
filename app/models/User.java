package models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/4/12.
 */
@Entity
@Table(name = "user")
public class User extends BasicModel{
    private String account;
    private String password;
    private String username;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static User getUser(String account){
        User user=User.find("account = ?", account).first();
        return user;
    }

    public static boolean login(String account,String password){
        User user=User.find("account = ? and password = ?",account,password).first();
        if(user!=null){
            return true;
        }else {
            return false;
        }
    }
}
