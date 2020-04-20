package com.sisucon.loopdaliy_server.Model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanEventRepository extends MongoRepository<PlanEventClass,Long> {
    PlanEventClass findPlanEventClassById(long id);
    int countPlanEventClassesByUserIdAndPlanId(long userId,long planId);
}
