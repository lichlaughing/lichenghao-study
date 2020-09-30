package com.lichenghao.entity;

import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Alias("User")
public class User {

    private Integer id;
    private String username;
    private Integer age;
    private Timestamp birthday;

    public User() {

    }

    public User(Integer id, String username, Integer age, Timestamp birthday) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
