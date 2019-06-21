package com.sisucon.loopdaliy_server.Model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserModelRepository  extends MongoRepository<UserModel,Long> {
    int countByUserName(String s);
    UserModel findUserModelByUserName(String userName);
}
