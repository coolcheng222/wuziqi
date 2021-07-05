package com.sealll.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author sealll
 * @time 2021/7/5 15:42
 */
//@ComponentScan("com.sealll")
//@PropertySource("jdbc.properties")
//@MapperScan(basePackages = "com.sealll.dao")
//@Configuration
public class SpringConfig1 {
    @Value("username")
    private String username;
    @Value("password")
    private String password;
    @Value("url")
    private String url;
    @Value("driver")
    private String driver;
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setBreakAfterAcquireFailure(true);
        druidDataSource.setFilters("config,stat");
        return druidDataSource;
    }

}
