package com.xuelin.coke.common.dto;

import com.xuelin.coke.domain.UserProfileInfo;

import java.util.Date;

public class CreateUserProfileInfo {

    private String name;
    private Integer age;
    private Date create_at;

    public CreateUserProfileInfo() {
    }

    public CreateUserProfileInfo(String name, Integer age, Date create_at) {
        this.name = name;
        this.age = age;
        this.create_at = create_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public static UserProfileInfo convert2UserProfileInfo(CreateUserProfileInfo createUserProfileInfo) {

        Date date = new Date();
        UserProfileInfo userProfileInfo = new UserProfileInfo();
        userProfileInfo.setName(createUserProfileInfo.getName());
        userProfileInfo.setAge(createUserProfileInfo.getAge());
        userProfileInfo.setCreateAt(date);

        return userProfileInfo;
    }
}
