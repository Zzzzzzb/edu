package com.zhengzb.edu.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@MapperScan(basePackages = "com.zhengzb.edu.mybatis.mapper")
@ComponentScan(basePackages = "com.zhengzb.edu.mybatis.service")
public class DomainAutoConfiguration {

}
