package com.xuelin.coke.dao;

import com.xuelin.coke.domain.UserProfileInfo;
import com.xuelin.coke.domain.UserProfileInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserProfileInfoMapper {
    int deleteByExample(UserProfileInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserProfileInfo record);

    int insertSelective(UserProfileInfo record);

    List<UserProfileInfo> selectByExample(UserProfileInfoExample example);

    UserProfileInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserProfileInfo record, @Param("example") UserProfileInfoExample example);

    int updateByExample(@Param("record") UserProfileInfo record, @Param("example") UserProfileInfoExample example);

    int updateByPrimaryKeySelective(UserProfileInfo record);

    int updateByPrimaryKey(UserProfileInfo record);
}