package utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import models.Image;
import play.Play;
import play.libs.Codec;

import java.io.File;

/**
 * Created by Administrator on 2015/4/20.
 */
public class AuthUtil {
    String AK;
    String SK;
    String bucketName;
    Auth auth;
    String url;
    private UploadManager uploadManager = new UploadManager();
    public AuthUtil(){
        AK=Play.configuration.getProperty("qiniu_AK");
        SK=Play.configuration.getProperty("qiniu_SK");
        bucketName=Play.configuration.getProperty("bucketName");
        url=Play.configuration.getProperty("qiniu_host");
        auth=Auth.create(AK,SK);
    }

    /**
     * ��ţ�ϴ��ӿ�
     * @param file �ļ���Ĭ��ͼƬ
     * @param type �ļ����ͣ��Ƿ�Ϊ��ͼ
     */
    public Image uploadFile(File file,int type,String uid){
        System.out.println("-->start upload");
       String key=getUUID()+file.getName();
        Image image=new Image();
       try {
           upload(file, key, getUpToken());
           if(type==1){
               image.setUrl(url + key);
               image.setIsBanner(true);
               image.setName(key);
               image.setOwnerId(uid);
               image.save();
           }
           System.out.println("-->end upload");
           return image;
       }catch (Exception e){
        e.printStackTrace();
           System.out.println("-->can't upload,something wrong");
           return null;
       }

    }

    public void upload(File file, String key, String upToken){
        try {
            Response res = uploadManager.put(file, key, upToken);
            // log.info(res);
            // log.info(res.bodyString());
            // Ret ret = res.jsonToObject(Ret.class);
            if(res.isOK()){
                //success
            }else {
                //
            }
        } catch (QiniuException e) {
            Response r = e.response;
            // ����ʧ��ʱ��״̬��Ϣ
            e.printStackTrace();
            try {
                // ��Ӧ���ı���Ϣ
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                e.printStackTrace();
                //ignore
            }
        }
    }

    // ���ϴ���ʹ��Ĭ�ϲ���
    private String getUpToken(){
        return auth.uploadToken(bucketName);
    }

    public String getUUID(){
        return Codec.UUID().replace("-", "");
    }
    public String getAK(){
        return this.AK;
    }

    public String getSK(){
        return this.SK;
    }
}
