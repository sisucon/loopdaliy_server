package com.sisucon.loopdaliy_server.Controller;

import com.sisucon.loopdaliy_server.Model.*;
import com.sisucon.loopdaliy_server.Utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "actionIcon")
public class ActionIconController {
    @Autowired
    private UserModelRepository userModelRepository;
    @Autowired
    private IconClassRepository iconClassRepository;
    @Autowired
    private ActionClassRepository actionClassRepository;

    @RequestMapping(value = "/upload")
    private ResponseEntity uploadIcon(@AuthenticationPrincipal UserModel userModel, @RequestParam(value = "actionId")long id, @RequestParam(value = "file")MultipartFile file){
        IconClass iconClass = new IconClass();
        iconClass.setActionId(id);
        iconClass.setId(iconClassRepository.count());
        iconClass.setUserNum(0);
        ReplyMessage replyMessage = Util.saveFile(file,"/actionIcon/"+id+"/",null,id+"_"+iconClass.getId());
        if (replyMessage.isResult()){
            iconClass.setFileName(replyMessage.getMessage());
            iconClassRepository.save(iconClass);
            replyMessage.setMessage("上传成功");
            return Util.getRE(replyMessage,HttpStatus.OK);
        }else {
            return Util.getRE(replyMessage, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/useIcon")
    private void useIcon(long id){
        IconClass iconClass = iconClassRepository.findIconClassById(id);
        iconClass.setUserNum(iconClass.getUserNum()+1);
        iconClassRepository.save(iconClass);
    }

    @RequestMapping(value = "/noUseAlong")
    private void noUseIcon(long id){
        IconClass iconClass = iconClassRepository.findIconClassById(id);
        iconClass.setUserNum(iconClass.getUserNum()-1);
        iconClassRepository.save(iconClass);
    }
}
