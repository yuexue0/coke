package com.xuelin.coke.config;

import com.xuelin.coke.interceptor.RequestParamInteceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@EnableWebMvc
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    RequestParamInteceptor requestParamInteceptor() {
        return new RequestParamInteceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration var0 = registry.addInterceptor(requestParamInteceptor());
        var0.addPathPatterns("/**");

    }



}
