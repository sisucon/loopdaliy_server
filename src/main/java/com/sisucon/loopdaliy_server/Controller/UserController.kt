package com.sisucon.loopdaliy_server.Controller

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.sisucon.loopdaliy_server.Model.ReplyMessage
import com.sisucon.loopdaliy_server.Model.UserModel
import com.sisucon.loopdaliy_server.Model.UserModelRepository
import com.sisucon.loopdaliy_server.Security.RoleList
import com.sisucon.loopdaliy_server.Utils.Util
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.*

import java.util.ArrayList

@RestController
@RequestMapping("user")
class UserController {

    data class UserJson(val username:String,val password:String)
     val logger = LoggerFactory.getLogger(javaClass) as Logger

    @Autowired
     val userModelRepository: UserModelRepository? = null

    @Autowired
     val gridFsTemplate: GridFsTemplate? = null

    @Autowired
     val mongoTemplate: MongoTemplate? = null

    @PostMapping("test")
     fun test(@AuthenticationPrincipal userModel: UserModel?): String {
        return if (userModel != null)
            "Test Success " + userModel.userName + " login " + userModel.myAuthorities.toString()
        else
            "Test success no login"
    }

    @RequestMapping( "register")
     fun registerUser(@RequestBody userJson:UserJson): ResponseEntity<*> {
        return if (userModelRepository!!.findUserModelByUserName(userJson.username) != null) {
            ResponseEntity(ReplyMessage(false, userJson.username + "用户名已被注册"), HttpStatus.NOT_FOUND)
        } else {
            val userModel = UserModel()
            userModel.id = userModelRepository.count()+1
            userModel.userName = userJson.username
            userModel.passWord = userJson.password
            val authorities = ArrayList<SimpleGrantedAuthority>()
            authorities.add(SimpleGrantedAuthority(RoleList.value(RoleList.Roles.NormalMember)))
            userModel.myAuthorities = authorities
            userModelRepository.save(userModel)
            ResponseEntity(ReplyMessage(true, "success register"), HttpStatus.OK)
        }
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @RequestMapping("/uploadAvator")
    private fun uploadAvator(@AuthenticationPrincipal userModel: UserModel, @RequestParam(value = "file") file: MultipartFile): ResponseEntity<*> {
        val replyMessage = Util.saveFile(file, "/avator/" + userModel.userName + "/", userModel.avatorFileName, userModel.userName + "_avator")
        return if (replyMessage.isResult) {
            userModel.avatorFileName = replyMessage.message
            userModelRepository!!.save(userModel)
            replyMessage.message = "上传成功"
            Util.getRE(replyMessage, HttpStatus.OK)
        } else {
            Util.getRE(replyMessage, HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping( "/myInfo")
    @JsonIgnoreProperties("passWord")
    private fun getMyInfo(@AuthenticationPrincipal userModel: UserModel): UserModel {
            return userModel
    }

    @RequestMapping( "/syanMyAttenAction")
    private fun syanMyAttendAction(@AuthenticationPrincipal userModel: UserModel, @RequestParam(value = "attendAction") attentAction: ArrayList<Long>): ResponseEntity<*> {
        userModel.attendAction = attentAction
        userModelRepository!!.save(userModel)
        return Util.getRE(ReplyMessage(true, "同步成功"), HttpStatus.OK)
    }



}
