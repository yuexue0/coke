package com.xuelin.coke.domain.fastjson;

import com.xuelin.coke.domain.fastjson.User;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {
    private String name;
    private List<User> users = new ArrayList<User>();
    public UserGroup(){}
    public UserGroup(String name,List<User> users){
        this.name = name;
        this.users = users;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Override
    public String toString() {
        return "UserGroup [name=" + name + ", users=" + users + "]";
    }
}
