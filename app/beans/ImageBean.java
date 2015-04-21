package beans;

import models.Image;

/**
 * Created by Administrator on 2015/4/12.
 */
public class ImageBean {
    private Integer isOk;
    private String id;
    private String url;
    private String name;
    private String message;
    private Object[]imges;

    public Object[] getImges() {
        return imges;
    }

    public void setImges(Object[] imges) {
        this.imges = imges;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsOk() {
        return isOk;
    }

    public void setIsOk(Integer isOk) {
        this.isOk = isOk;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
