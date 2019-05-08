package enn.testone.controller;

import enn.testone.entity.Responsed;
import enn.testone.service.ImageUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class File {
    @Autowired
    ImageUpload imageUpload;

    @Value("${filePath}")
    private String filePath;

    @RequestMapping(value = "/uploadImg")
    public Responsed imageUp(@RequestParam("file") MultipartFile file){
        log.info(filePath);//服务器上上传文件的物理路径
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        log.info(file.toString());

        String uploadPath = "static/images/";  // 服务器上上传文件的相对路径

        String imageURL = imageUpload.uploadImg( file, uploadPath, filePath );

        log.info("imageURL:"+imageURL);

        Responsed responsed = new Responsed();
        responsed.setCode(200);
        responsed.setMessage("上传成功！");
        responsed.setData(imageURL);
        return responsed;
    }
}
