import com.qbhy.poster.PosterApplication;
import com.qbhy.poster.kernal.qiniu.QiniuConfig;
import com.qbhy.poster.kernal.qiniu.QiniuUploader;
import com.qbhy.poster.kernal.upyun.UpYunUploader;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PosterApplication.class)
public class UploaderTest {

    @Autowired
    QiniuUploader qiniuUploader;
    @Autowired
    QiniuConfig qiniuConfig;

    @Autowired
    UpYunUploader upYunUploader;

    private File makeTestFile() throws IOException {
        String path = "/Users/qbhy/project/java/poster-generater/downloads/test.txt";
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        // write
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("测试文件");
        bw.flush();
        bw.close();
        file.deleteOnExit();

        return file;
    }


    @Test
    public void testQiniuUploader() throws QiniuException, IOException {
        // 测试七牛自带的 uploader
        Response response = qiniuUploader.getUploadManager().put("测试".getBytes(), "poster-upload.log", qiniuUploader.getAuth().uploadToken(qiniuConfig.getBucket()));
        Assert.assertTrue(response.isOK());

        Assert.assertTrue(qiniuUploader.upload(makeTestFile()).isSuccessful());
    }

    @Test
    public void testUpyunUploader() throws IOException {
        Assert.assertTrue(upYunUploader.upload(makeTestFile()).isSuccessful());
    }
}
