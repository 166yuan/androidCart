package controllers;

import beans.UserBean;
import models.User;
import play.mvc.Controller;

/**
 * Created by Administrator on 2015/4/20.
 */
public class Users extends Controller{

    public static void addUser(String account,String password){
        password=password.substring(1,password.length());
         UserBean userBean =new UserBean();
        if(account!=null&&password!=null){
            if(User.getUser(account)==null){
                try{
                    User user=new User();
                    user.setAccount(account);
                    user.setPassword(password);
                    user.save();
                    userBean.setIsOk(1);
                    userBean.setAccount(account);
                    userBean.setMessage("success new user");
                }catch (Exception e){
                    e.printStackTrace();
                    userBean.setIsOk(-1);
                    userBean.setMessage("Unknown error of server");
                }
            }else {
                userBean.setIsOk(0);
                userBean.setMessage("user is already exist");
            }
        }else {
            userBean.setIsOk(-1);
            userBean.setMessage("username and password is null,please check it");
        }
        renderJSON(userBean);
    }
    public static void login(String account,String password){
        UserBean userBean =new UserBean();
        if(User.getUser(account)==null){
            userBean.setIsOk(0);
            userBean.setMessage("find no user");
        }else {
            if (User.login(account,password)){
                userBean.setIsOk(1);
                session.put("account",account);
                session.put("userId",User.getUser(account).getId());
                userBean.setIsOk(1);
                userBean.setMessage("success login");
            }else {
                userBean.setIsOk(-1);
                userBean.setMessage("wrong password");
            }
        }
        renderJSON(userBean);
    }

    public static void isLogin(String account){
        UserBean userBean =new UserBean();
        if(session.get(account)!=null){
            userBean.setIsOk(1);
            userBean.setMessage("user logined");
        }else {
            userBean.setIsOk(0);
            userBean.setMessage("unlogin");
        }
        renderJSON(userBean);
    }

    public static void logout(String account){
        session.put("account",null);
        UserBean userBean=new UserBean();
        userBean.setIsOk(1);
        userBean.setMessage("success logout");
    }
}
