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

@RestController
@RequestMapping(value = "actionEvent")
public class AcionEventController {
    @Autowired
    private UserModelRepository userModelRepository;
    @Autowired
    private ActionEventRepository actionEventRepository;
    @Autowired
    private ActionClassRepository actionClassRepository;

    @RequestMapping(value = "/upload")
    private ResponseEntity uploadEvent(@AuthenticationPrincipal UserModel userModel, @RequestParam(value = "actionId")long actionId,@RequestParam(value = "isPrivate")boolean isPrivate,@RequestParam(value = "updateTime")long updateTime,@RequestParam(value = "holdTime") int holdTime,@RequestParam(value = "totalTime")long AllTime){
        ActionEvent actionEvent = new ActionEvent();
        ActionClass actionClass = actionClassRepository.findActionClassById(actionId);
        actionClass.setSuccessTime(actionClass.getSuccessTime()+1);
        actionClassRepository.save(actionClass);
        actionEvent.setId(actionEventRepository.count());
        actionEvent.setActionId(actionId);
        actionEvent.setHoldTime(holdTime);
        actionEvent.setPrivate(isPrivate);
        actionEvent.setUpdateTime(updateTime);
        actionEvent.setUserId(userModel.getId());
        actionEvent.setALLTime(AllTime);
        actionEventRepository.save(actionEvent);
        return Util.getRE(new ReplyMessage(true,"上传成功"), HttpStatus.OK);
    }
}
