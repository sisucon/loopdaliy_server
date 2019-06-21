package com.sisucon.loopdaliy_server.Model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface ActionClassRepository extends MongoRepository<ActionClass,Long> {
    ActionClass findActionClassById(long id);
    ArrayList<ActionClass> findActionClassesByName(String name);
    Boolean existsActionClassByName(String name);

}
