package cn.com.lichenghao.springbootmybatis.model;

import java.io.Serializable;

/**
 * tb_user_test
 *
 * @author
 */
public class TbUserTest implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer ag;

    public TbUserTest() {

    }

    public TbUserTest(String id, String name, Integer ag) {
        this.id = id;
        this.name = name;
        this.ag = ag;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAg() {
        return ag;
    }

    public void setAg(Integer ag) {
        this.ag = ag;
    }

    @Override
    public String toString() {
        return "TbUserTest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ag=" + ag +
                '}';
    }
}