package com.sisucon.loopdaliy_server.Controller

import com.sisucon.loopdaliy_server.Model.*
import com.sisucon.loopdaliy_server.Service.PlanService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/plan")
class PlanController(private val planService: PlanService) {
    @Autowired
    val userModelRepository : UserModelRepository?= null
    private val logger = LoggerFactory.getLogger(javaClass) as Logger

    @RequestMapping("/createPlan")
    fun createPlan(@AuthenticationPrincipal userModel: UserModel?, @RequestBody planClass:PlanJson):ResponseEntity<*>{
     val temp = PlanClass(id = planService.createId()
     ,userId = userModel!!.id, name = planClass.name,isLoop = planClass.isLoop,loopTime = planClass.loopTime,info = "",startTime = planClass.startTime,isRemind = planClass.isRemind,isFinish = planClass.isFinish)
        planService.saveItem(temp)
        logger.info(userModel.userName+"上传了日程"+temp.name)
       return ResponseEntity(ReplyMessage(true, temp.toString()), HttpStatus.OK)
    }

}