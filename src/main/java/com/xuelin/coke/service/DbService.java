package com.xuelin.coke.service;

import com.xuelin.coke.common.utils.ApplicationContextUtil;
import com.xuelin.coke.dao.UserProfileInfoMapper;
import com.xuelin.coke.domain.UserProfileInfo;
import com.xuelin.coke.domain.UserProfileInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class DbService {

    @Autowired
    private UserProfileInfoMapper userProfileInfoMapper;

    @Value("${coke.third-host}")
    private String thirdHost;

    private String thirdCom = ApplicationContextUtil.getApplicationContext().getEnvironment().getProperty("coke.third-com");


    /**
     * 获取*.yml配置文件参数两种方式
     * @return
     */
    public String getYamlConf() {
        StringBuilder sb = new StringBuilder();
        sb.append("value方式：");
        sb.append(thirdHost);
        sb.append("，ApplicationContextUtil方式：");
        sb.append(thirdCom);
        return sb.toString();
    }

    /**
     * 中文乱码转换
     * @param des
     * @return
     */
    public String yamlZh2CharsetName(String des) {

        String name = ApplicationContextUtil.getApplicationContext().getEnvironment().getProperty(des);
        String tempName = "";
        try {
            tempName = new String(name.getBytes("ISO-8859-1"), "utf-8");
            return tempName;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tempName;
    }

    public UserProfileInfo findByID(Integer id) {

        return userProfileInfoMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = "UserProfileInfo", key = "#root.args[0]", unless = "#result eq null ")
    public UserProfileInfo findByname(String name) {

        UserProfileInfoExample userProfileInfoExample = new UserProfileInfoExample();
        UserProfileInfoExample.Criteria criteria = userProfileInfoExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<UserProfileInfo> userProfileInfos = userProfileInfoMapper.selectByExample(userProfileInfoExample);

        if (CollectionUtils.isEmpty(userProfileInfos)) {
            throw new RuntimeException("Can't find records based on the  in the user_profile_info table ");
        }

        if (!CollectionUtils.isEmpty(userProfileInfos) && userProfileInfos.size()>1) {
//            throw new RuntimeException(" Query multiple records based on the  in the user_profile_info table ");
        }
        return userProfileInfos.get(0);
    }


    public List<UserProfileInfo> userList() {

        UserProfileInfoExample userProfileInfoExample = new UserProfileInfoExample();
        UserProfileInfoExample.Criteria criteria = userProfileInfoExample.createCriteria();
        criteria.andAgeEqualTo(35);
        List<UserProfileInfo> userProfileInfos = userProfileInfoMapper.selectByExample(userProfileInfoExample);
        return userProfileInfos;
    }

    @Transactional
    public int create(UserProfileInfo userProfileInfo) {

        return userProfileInfoMapper.insertSelective(userProfileInfo);
    }

    @Transactional
    @CacheEvict(value = "UserProfileInfo", key = "#userProfileInfo.name", condition = "#result > 0")
    public int update(UserProfileInfo userProfileInfo) {
        UserProfileInfoExample userProfileInfoExample = new UserProfileInfoExample();
        UserProfileInfoExample.Criteria criteria = userProfileInfoExample.createCriteria();
        criteria.andNameEqualTo(userProfileInfo.getName());

        return userProfileInfoMapper.updateByExampleSelective(userProfileInfo, userProfileInfoExample);
    }

}
