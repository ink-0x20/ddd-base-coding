package com.inkblogdb.ddd.application.config;

import org.h2.server.web.JakartaWebServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.inkblogdb.ddd.infrastructure.database")
public class MyBatisConfig {

  @Bean
  public ServletRegistrationBean<JakartaWebServlet> h2servletRegistration() {
    return new ServletRegistrationBean<>(new JakartaWebServlet(), "/h2-console/*");
  }

}
