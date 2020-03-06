package com.sisucon.loopdaliy_server.Model

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.ArrayList

@Repository
interface PlanClassRepository : MongoRepository<PlanClass,Long>{
    fun findPlanClassById(id:Long) : PlanClass
    fun findPlanClassesByUserId(id:Long) : List<PlanClass>
}