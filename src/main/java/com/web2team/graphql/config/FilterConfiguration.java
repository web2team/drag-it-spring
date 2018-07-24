//package com.web2team.graphql.config;
//
//import com.web2team.graphql.Filter.TokenFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class FilterConfiguration implements WebMvcConfigurer {
//
//  @Bean
//  public FilterRegistrationBean<TokenFilter> tokenFilterRegistration() {
//    FilterRegistrationBean<TokenFilter> registration = new FilterRegistrationBean<>();
//    registration.setFilter(new TokenFilter());
////    registration.addUrlPatterns("/graphiql/*");
//    registration.addUrlPatterns("/graphql/*");
//    registration.setName("graphql Auth filter");
//
//    registration.setOrder(1);
//
//    return registration;
//  }
//}
