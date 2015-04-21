package controllers;

import beans.ImageBean;
import models.Image;
import play.Play;
import play.mvc.Controller;
import utils.AuthUtil;

import javax.security.auth.login.Configuration;
import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2015/4/20.
 */
public class Images extends Controller{

    public static void getImage(String id){
        ImageBean imageBean=new ImageBean();
        Image image=Image.findById(id);
        if(image!=null){
            imageBean.setIsOk(1);
            imageBean.setUrl(image.getUrl());
            imageBean.setName(image.getName());
            imageBean.setId(image.getId());
            imageBean.setMessage("success");
        }else {
            imageBean.setIsOk(-1);
            imageBean.setId(id);
            imageBean.setMessage("can't find Image");
        }
    }

    public static void newImage(File file){
        ImageBean imageBean=new ImageBean();

    }

    public static void addBanner(File file){
        ImageBean imageBean=new ImageBean();
        AuthUtil authUtil=new AuthUtil();
        String uid=session.get("userId");
        if(uid!=null){
            Image image=authUtil.uploadFile(file, 1, uid);
            imageBean.setIsOk(1);
            imageBean.setMessage("success upload");
            imageBean.setUrl(image.getUrl());
            imageBean.setId(image.getId());
            imageBean.setName(image.getName());
        }else {
            imageBean.setIsOk(0);
            imageBean.setMessage("please login in");
            System.out.println("please login in");
        }
        renderJSON(imageBean);
    }
    public static void getBanner(){
        ImageBean imageBean=new ImageBean();
        List<Image> list=Image.getBanner();
        Image []images=new Image[list.size()];
        if(list.size()>0){
        imageBean.setIsOk(1);
            if(list.size()>=3){
                for(int i=0;i<=2;i++){
                    images[i]=list.get(i);
                }
            }else {
                for(int i=0;i<list.size();i++){
                    images[i]=list.get(i);
                }
            }
            imageBean.setImges(images);
            imageBean.setMessage("success get banner");
        } else {
            imageBean.setIsOk(-1);
            imageBean.setMessage("no banner found");
        }
        renderJSON(imageBean);
    }

    public static void Banner(){
        render();
    }


}
