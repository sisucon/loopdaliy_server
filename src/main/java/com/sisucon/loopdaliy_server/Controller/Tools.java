package com.sisucon.loopdaliy_server.Controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sisucon.loopdaliy_server.Model.ReplyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Tools {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    public  ResponseEntity saveImage(String fileName, MultipartFile file){
        DBObject object = new BasicDBObject();
        object.put("createDate",new Date());
        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();
            gridFsTemplate.store(inputStream,fileName,"image",object);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("System Exception while handling request");
        }
        return new ResponseEntity<>(new ReplyMessage(true,"保存图片成功"), HttpStatus.OK);
    }
}
