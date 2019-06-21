package com.sisucon.loopdaliy_server.Controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sisucon.loopdaliy_server.Model.*;
import com.sisucon.loopdaliy_server.Utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "action")
public class ActionController {
    @Autowired
    private UserModelRepository userModelRepository;
    @Autowired
    private ActionClassRepository actionClassRepository;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @RequestMapping(value = "/createAction")
    private ResponseEntity createAction(@RequestParam(value = "name") String name, @RequestParam(value = "file")MultipartFile file,@RequestParam(value = "loopTime")long loopTime,@RequestParam(value = "type")int type) {
        ReplyMessage replyMessage = null;
        if ((replyMessage =  Util.saveFile(file,"/actionDefault/"+(actionClassRepository.count()+1)+"/",null,name+"_default")).isResult()){
            ActionClass actionClass = new ActionClass();
            actionClass.setId(actionClassRepository.count()+1);
            actionClass.setName(name);
            actionClass.setUploadTime(new Date().getTime());
            actionClass.setType(type);
            actionClass.setLoopTime(loopTime);
            actionClass.setImageName(replyMessage.getMessage());
            actionClass.setAccept(false);
            actionClassRepository.save(actionClass);
            return Util.getRE(new ReplyMessage(true,"上传成功"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ReplyMessage(false,replyMessage.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
@RequestMapping(value = "/attendAction/{actionId}")
    private ResponseEntity attendAction(@AuthenticationPrincipal UserModel userModel, @PathVariable long actionId){
        if (    userModel.getAttendAction()!=null &&    userModel.getAttendAction().indexOf(actionId)>=0){
            return Util.getRE(new ReplyMessage(false,"你已经参加过啦!"),HttpStatus.OK);
        }
        ArrayList<Long> actionIdList = userModel.getAttendAction();
        actionIdList.add(actionId);
        ActionClass a =  actionClassRepository.findActionClassById(actionId);
        a.addAttendNumber();
        actionClassRepository.save(a);
        userModel.setAttendAction(actionIdList);
        userModelRepository.save(userModel);
        return Util.getRE(new ReplyMessage(true,"添加成功"),HttpStatus.OK);
    }

    @RequestMapping(value = "/getAction")
    private List<ActionClass> getAllAction(){
        return actionClassRepository.findAll();
    }


    @RequestMapping(value = "/getAction/{id}")
    private ActionClass getAction(@PathVariable String id){
        return actionClassRepository.findActionClassById(Long.valueOf(id));
    }

    @RequestMapping(value = "/cancelAction")
    private ResponseEntity cancelAction(@AuthenticationPrincipal UserModel userModel,@RequestParam(value = "id")long actionId){
        ArrayList<Long> actionIdList = userModel.getAttendAction();
        actionIdList.remove(actionId);
        userModel.setAttendAction(actionIdList);
        ActionClass a =  actionClassRepository.findActionClassById(actionId);
        a.cancelAttendNumber();
        actionClassRepository.save(a);
        userModelRepository.save(userModel);
        return Util.getRE(new ReplyMessage(true,"取消成功"),HttpStatus.OK);
    }

    @RequestMapping(value = "/getMyAttendAction")
    private List<ActionClass> getMyAttendAction(@AuthenticationPrincipal UserModel userModel){
        List<ActionClass> actionClassList = new ArrayList<>();
        List<Long> attendList = userModel.getAttendAction();
        for (Long aLong : attendList) {
            actionClassList.add(actionClassRepository.findActionClassById(aLong));
        }
        return actionClassList;
    }

}
