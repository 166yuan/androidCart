package controllers;

import beans.AddBean;
import beans.ImageBean;
import play.*;
import play.libs.Codec;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
    public static void addUser(String userName,String accoutPassw){
        accoutPassw=accoutPassw.substring(1,accoutPassw.length());
        AddBean addBean=new AddBean();
        if(userName!=null&&accoutPassw!=null){
            if(!User.getUser(userName)){
                try{
                    User user=new User();
                    user.setUserName(userName);
                    user.setAccoutPassw(accoutPassw);
                    user.save();
                    addBean.setIsOk(1+"");
                    addBean.setUserName(userName);
                    addBean.setMessage("success new user");
                }catch (Exception e){
                    e.printStackTrace();
                    addBean.setIsOk(-1+"");
                    addBean.setMessage("Unknown error of server");
                }
            }else {
                addBean.setIsOk(0+"");
                addBean.setMessage("user is already exist");
            }
        }else {
            addBean.setIsOk(-1+"");
            addBean.setMessage("username and password is null,please check it");
        }
        renderJSON(addBean);
    }

    public static void login(String userName,String accoutPassw){
        AddBean addBean=new AddBean();
        if (User.userLogin(userName,accoutPassw)){
            addBean.setIsOk(1+"");
            Http.Response.current().setCookie("userName",userName,"10h");
            addBean.setIsOk(1+"");
            addBean.setMessage("success login");
        }else {
            addBean.setIsOk(-1+"");
            addBean.setMessage("user not found");
        }
        renderJSON(addBean);
    }

    public static void getImage(String imgName){
        ImageBean imageBean=new ImageBean();
        Image image=Image.getImage(imgName);
        if(image!=null){
            imageBean.setIsOk(1+"");
            imageBean.setImgStr(image.getImgStr());
            imageBean.setImgName(image.getImgName());
            imageBean.setMessage("success");
        }else {
            imageBean.setIsOk(-1+"");
            imageBean.setMessage("can't find Image");
        }
    }

    public static void newImage(String imgName,String imgStr){
        ImageBean imageBean=new ImageBean();
        Image image=Image.getImage(imgName);
        if(image==null){
           Image image1=new Image();
            image1.setImgStr(imgStr);
            image1.setImgName(imgName);
            image1.save();
            imageBean.setIsOk(1+"");
            imageBean.setMessage("success");
        }else {
            imageBean.setIsOk(-1+"");
            imageBean.setMessage("image is exits");
        }
    }
}