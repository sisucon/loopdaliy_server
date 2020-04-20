package com.sisucon.loopdaliy_server.Utils;

import com.sisucon.loopdaliy_server.Model.ReplyMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Util {
//    private static String uploadDir = "/var/www/html/upload";
private static String uploadDir = "F:/upload";

    public static ReplyMessage saveFile(MultipartFile file, String path ,String lastFileName,String name){
        if (file.getOriginalFilename()==null){
            return new ReplyMessage(false,"图片资源不可用");
        }

        if (file.isEmpty()||file.getOriginalFilename()==null){
            return new ReplyMessage(false,"文件或文件名不能为空");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        path = uploadDir+path;
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File lastFile = new File(path+lastFileName);
        if (lastFile.exists()){
            lastFile.delete();
        }
        String finalName = name+suffixName;
        File newFile = new File(path+finalName);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new ReplyMessage(false,"服务器出错");
        }
        return new ReplyMessage(true,finalName);
    }

    public static ResponseEntity getRE(ReplyMessage replyMessage,HttpStatus httpStatus){
        return new ResponseEntity<>(replyMessage,httpStatus);
    }
}
