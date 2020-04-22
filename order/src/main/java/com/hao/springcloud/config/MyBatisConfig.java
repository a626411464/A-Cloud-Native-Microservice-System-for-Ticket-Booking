package com.hao.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.hao.springcloud.dao"})
public class MyBatisConfig {
}
