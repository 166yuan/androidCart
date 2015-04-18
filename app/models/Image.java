package models;

/**
 * Created by Administrator on 2015/4/12.
 */
public class Image extends BasicModel {
    private String imgStr;
    private String imgName;

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public static Image getImage(String name){
        Image image=Image.find("imgName = ?",name).first();
        return image;
    }
}
