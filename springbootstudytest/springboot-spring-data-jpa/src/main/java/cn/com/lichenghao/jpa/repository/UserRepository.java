package cn.com.lichenghao.jpa.repository;

import cn.com.lichenghao.jpa.model.TbUserTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TbUserTestEntity, String> {
}
