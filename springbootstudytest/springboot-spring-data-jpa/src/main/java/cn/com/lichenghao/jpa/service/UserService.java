package cn.com.lichenghao.jpa.service;

import cn.com.lichenghao.jpa.model.TbUserTestEntity;

public interface UserService {

    TbUserTestEntity addUser(TbUserTestEntity userTestEntity);

    void deleteUser(String userId);

    TbUserTestEntity getUser(String userId);

}
