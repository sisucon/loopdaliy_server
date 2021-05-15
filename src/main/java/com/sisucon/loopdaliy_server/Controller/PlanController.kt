package com.sisucon.loopdaliy_server.Controller

import com.sisucon.loopdaliy_server.Model.*
import com.sisucon.loopdaliy_server.Service.PlanService
import com.sisucon.loopdaliy_server.Utils.Util
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.math.log

@RestController
@RequestMapping("/plan")
class PlanController(private val planService: PlanService) {
    @Autowired
    val userModelRepository : UserModelRepository?= null
    val planEventRepository : PlanEventRepository?= null
    private val logger = LoggerFactory.getLogger(javaClass) as Logger

    @RequestMapping("/createPlan")
    fun createPlan(@AuthenticationPrincipal userModel: UserModel?, @RequestBody planClass:PlanJson):PlanClass{
     val temp = PlanClass(id = planService.createId()
     ,userId = userModel!!.id, name = planClass.name,isLoop = planClass.isLoop,loopTime = planClass.loopTime,info = "",startTime = planClass.startTime,isRemind = planClass.isRemind,isFinish = planClass.isFinish)
        planService.saveItem(temp)
        logger.info(userModel.userName+" 上传了日程:"+temp.name)
       return temp
    }

    @RequestMapping("/getPlan")
    fun getMyPlan(@AuthenticationPrincipal userModel: UserModel?):List<PlanClass>?{
        if(userModel!=null){
            logger.info(userModel.userName+" 同步了计划")
            return planService.findByUserId(userModel)
        }
        return null
    }

    @RequestMapping("/createPlanEvent")
    fun createPlanEvent(@AuthenticationPrincipal userModel: UserModel?,@RequestBody planEventJson:PlanEventJson):PlanEventClass{
        val planEventClass = PlanEventClass()
        planEventClass.id = planEventRepository!!.count()+1
        planEventClass.index = planEventRepository.countPlanEventClassesByUserIdAndPlanId(userModel!!.id,planEventJson.planId)
        planEventClass.isFinish = planEventJson.isSuccess
        planEventClass.planId = planEventJson.planId
        planEventClass.userId = userModel.id
        planEventClass.updateTime = Date().time
        planEventRepository.save(planEventClass)
        return planEventClass
    }

    @RequestMapping("/deletePlan/{planId}")
    fun deletePlanEvent(@AuthenticationPrincipal userModel: UserModel,@PathVariable planId:Long)  {
         planService.deleteByUserIdAndPlanId(userModel,planId)
    }

    @RequestMapping("/updatePlan")
    fun updatePlan(@AuthenticationPrincipal userModel: UserModel, @RequestBody planClass:PlanJson):PlanClass{
        var planDb = planService.findByUserIdAndPlanId(userModel,planClass.remoteId)
        planDb.isFinish = planClass.isFinish
        planDb.isLoop = planClass.isLoop
        planDb.isRemind = planClass.isRemind
        planDb.loopTime = planClass.loopTime
        planDb.name = planClass.name
        planDb.startTime =planClass.startTime
        logger.info(userModel.userName+" 删除了计划"+planClass.name)
        planService.saveItem(planDb)
        return planDb
    }

}