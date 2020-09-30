package cn.com.lichenghao.springbootmybatis.dao;

import cn.com.lichenghao.springbootmybatis.model.TbUserTest;
import org.springframework.stereotype.Component;

@Component
public interface TbUserTestDao {
    int deleteByPrimaryKey(String id);

    int insert(TbUserTest record);

    int insertSelective(TbUserTest record);

    TbUserTest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbUserTest record);

    int updateByPrimaryKey(TbUserTest record);
}