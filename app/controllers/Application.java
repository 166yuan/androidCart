package controllers;

import beans.ImageBean;
import beans.UserBean;
import play.mvc.*;

import models.*;

public class Application extends Controller {
    public static void index(){
        render();
    }

    public static void login(String account,String password){
        UserBean userBean =new UserBean();
        if(User.getUser(account)==null){
            userBean.setIsOk(0);
            userBean.setMessage("find no user");
            renderJSON(userBean);
        }else {
            if (User.login(account,password)){
                userBean.setIsOk(1);
                session.put("account",account);
                session.put("userId",User.getUser(account).getId());
                userBean.setIsOk(1);
                userBean.setMessage("success login");
                success();
            }else {
                userBean.setIsOk(-1);
                userBean.setMessage("wrong password");
                renderJSON(userBean);
            }
        }

    }

    public static void success(){
        render();
    }
}