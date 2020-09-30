package cn.com.lichenghao.jpa.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Proxy(lazy = false)
@Table(name = "tb_user_test", schema = "springbootstudytest", catalog = "")
public class TbUserTestEntity {

    private String id;
    private String name;
    private Integer ag;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ag")
    public Integer getAg() {
        return ag;
    }

    public void setAg(Integer ag) {
        this.ag = ag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbUserTestEntity that = (TbUserTestEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(ag, that.ag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ag);
    }

    @Override
    public String toString() {
        return "TbUserTestEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ag=" + ag +
                '}';
    }
}
