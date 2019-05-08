package enn.testone.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImageUpload {

    public String uploadImg(MultipartFile file, String uploadPath, String physicalPath){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formatStr =formatter.format(new Date());
        String filePath = physicalPath+ formatStr + "\\" + file.getOriginalFilename();

        try {
            File targetFile=new File(filePath);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return uploadPath + "/" + file.getOriginalFilename();
        return filePath;
    }

}
