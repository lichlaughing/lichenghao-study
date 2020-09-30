package cn.com.lichenghao.springbootmybatis.mapper;

import cn.com.lichenghao.springbootmybatis.dao.TbUserTestDao;
import cn.com.lichenghao.springbootmybatis.model.TbUserTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TbUserTestDaoTest {

    @Autowired
    private TbUserTestDao tbUserTestDao;

    /**
     * 增
     */
    @Test
    //@Ignore
    public void testA() {
        TbUserTest tbUserTest = new TbUserTest("1", "Tom", 100);
        int insert = tbUserTestDao.insert(tbUserTest);
        System.out.println("insert:" + insert);
    }

    /**
     * 查
     */
    @Test
    //@Ignore
    public void testB() {
        TbUserTest tbUserTest = tbUserTestDao.selectByPrimaryKey("1");
        System.out.println("selectByPrimaryKey:" + tbUserTest);
    }

    /**
     * 改
     */
    @Test
    //@Ignore
    public void testC() {
        TbUserTest tbUserTest = tbUserTestDao.selectByPrimaryKey("1");
        tbUserTest.setName("Tom01");
        int i = tbUserTestDao.updateByPrimaryKey(tbUserTest);
        System.out.println("updateByPrimaryKey:" + i);
    }

    /**
     * 删
     */
    @Test
    //@Ignore
    public void testD() {
        TbUserTest tbUserTest = tbUserTestDao.selectByPrimaryKey("1");
        int i = tbUserTestDao.deleteByPrimaryKey(tbUserTest.getId());
        System.out.println("deleteByPrimaryKey:" + i);
    }
}
