package com.sisucon.loopdaliy_server.Model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface IconClassRepository extends MongoRepository<IconClass,Long> {
    IconClass findIconClassById(long id);
    ArrayList<IconClass> findIconClassesByActionId(long id);
}

