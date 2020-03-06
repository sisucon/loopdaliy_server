package com.sisucon.loopdaliy_server.Model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.ArrayList

class PlanClass (
        @Id
        var id:Long ,
        var name:String,
        var userId : Long,
        var isLoop:Boolean,
        var loopTime:Long,
        var info : String="" ,
        var startTime : Long
        ,var isRemind : Boolean
,var isFinish:Boolean
)



