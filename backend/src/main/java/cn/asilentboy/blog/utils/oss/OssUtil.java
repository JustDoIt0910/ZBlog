package cn.asilentboy.blog.utils.oss;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class OssUtil {

    private final UploadManager uploadManager;

    private final String publicPath;

    private final String access_key;

    private final String secret_key;

    private final String bucket;

    public OssUtil(@Autowired Environment env) {
        Configuration cfg = new Configuration(Region.region0());
        uploadManager = new UploadManager(cfg);
        access_key = env.getProperty("oss.access-key");
        secret_key = env.getProperty("oss.secret-key");
        bucket = env.getProperty("oss.bucket");
        publicPath = env.getProperty("oss.public-path");
    }

    public String upload(String key, byte[] data) {
        Auth auth = Auth.create(access_key, secret_key);
        String upToken = auth.uploadToken(bucket);
        try {
            uploadManager.put(data, key, upToken);
            String encodedFileName = URLEncoder.encode(key, "utf-8").replace("+", "%20");
            return String.format("%s/%s", publicPath, encodedFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
