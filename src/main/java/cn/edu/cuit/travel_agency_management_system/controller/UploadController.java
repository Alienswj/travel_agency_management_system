package cn.edu.cuit.travel_agency_management_system.controller;


import cn.edu.cuit.travel_agency_management_system.entity.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {


    @PostMapping("trip_pic")
    public RespBean uploadTripPic(MultipartFile file) throws IOException {
        String oldName=file.getOriginalFilename();
        System.out.println(oldName);
        File folder=new File("F:\\a学习\\毕业论文\\pic\\trip_pic\\"+oldName);

        file.transferTo(folder);

        return RespBean.ok("上传成功！");
    }
}
