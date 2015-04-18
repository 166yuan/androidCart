package models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/4/12.
 */
@Entity
@Table(name = "user")
public class User extends BasicModel{
    private String userName;
    private String accoutPassw;

    public String getAccoutPassw() {
        return accoutPassw;
    }

    public void setAccoutPassw(String accoutPassw) {
        this.accoutPassw = accoutPassw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static boolean getUser(String userName){
        User u= find("userName = ? ",userName).first();
        if(u!=null){
            return true;
        }else {
            return false;
        }
    }
    public static boolean userLogin(String userName,String password){
        User u=find("userName = ? and accoutPassw=?",userName,password).first();
        if(u==null){
            return false;
        }else {
            return true;
        }
    }
}
