package cn.com.lichenghao.jpa.service.impl;

import cn.com.lichenghao.jpa.model.TbUserTestEntity;
import cn.com.lichenghao.jpa.repository.UserRepository;
import cn.com.lichenghao.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public TbUserTestEntity addUser(TbUserTestEntity userTestEntity) {
        TbUserTestEntity save = userRepository.save(userTestEntity);
        return save;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public TbUserTestEntity getUser(String userId) {
        return userRepository.getOne(userId);
    }
}
