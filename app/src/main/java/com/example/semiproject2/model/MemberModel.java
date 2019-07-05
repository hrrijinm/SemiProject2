package com.example.semiproject2.model;

import java.io.Serializable;

public class MemberModel implements Serializable {
    private String id;
    private String pwd;
    private String name;
    private String birth;

    @Override
    public String toString() {
        return "Member{" + "id='" + id + '\'' + ", pwd='" + pwd + '\'' + ", name='" + name + '\'' + ", birth='" + birth + '\'' + '}';
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
}
