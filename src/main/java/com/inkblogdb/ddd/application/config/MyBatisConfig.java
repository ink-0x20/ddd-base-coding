package com.inkblogdb.ddd.application.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.h2.server.web.JakartaWebServlet;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.inkblogdb.ddd.infrastructure.database")
public class MyBatisConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource[] mappers = resolver.getResources("classpath:mapper/**/*.xml");
    sessionFactoryBean.setMapperLocations(mappers);
    return sessionFactoryBean.getObject();
  }

  @Bean
  public ServletRegistrationBean<JakartaWebServlet> h2servletRegistration() {
    return new ServletRegistrationBean<>(new JakartaWebServlet(), "/h2-console/*");
  }

}
