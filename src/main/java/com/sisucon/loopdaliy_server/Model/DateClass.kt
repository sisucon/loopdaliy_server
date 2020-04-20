package com.sisucon.loopdaliy_server.Model

import java.util.*

data class PlanJson(var name:String ,var isLoop:Boolean,var loopTime:Long ,var startTime : Long,var isRemind : Boolean,var isFinish:Boolean,var remoteId:Long)
data class PlanEventJson(var startDay: Date,var planId:Long,var isSuccess:Boolean,var time:Date)