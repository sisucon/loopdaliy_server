package com.sisucon.loopdaliy_server.Service

import com.sisucon.loopdaliy_server.Model.PlanClass
import com.sisucon.loopdaliy_server.Model.PlanClassRepository
import com.sisucon.loopdaliy_server.Model.UserModelRepository
import org.springframework.stereotype.Service

@Service
class PlanService (private val userModelRepository: UserModelRepository,
                   private val planClassRepository: PlanClassRepository){

    fun createId():Long{
        return planClassRepository.count()+1
    }

    fun saveItem(planClass: PlanClass){
        planClassRepository.save(planClass)
    }
}