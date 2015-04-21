package models;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Administrator on 2015/4/12.
 */
 @Entity
@Table(name = "image")
public class Image extends BasicModel {
    private String url;
    private String name;
    private String ownerId;
    private Boolean isBanner;
    private Boolean isUsed;
    public Image(){
        this.setIsBanner(false);
        this.setIsUsed(true);
    }
    public static List<Image> getBanner(){
        return Image.find("isBanner =?",true).fetch();
    }
    public Boolean isUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Boolean isBanner() {
        return isBanner;
    }

    public void setIsBanner(Boolean isBanner) {
        this.isBanner = isBanner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
