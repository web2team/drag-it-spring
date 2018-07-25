package com.web2team.security.config;

import com.web2team.security.JwtAuthenticationEntryPoint;
import com.web2team.security.JwtAuthenticationProvider;
import com.web2team.security.filter.JwtAuthenticationTokenFilter;
import com.web2team.security.handler.JwtAuthenticationFailureHandler;
import com.web2team.security.handler.JwtAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationEntryPoint unauthorizedHandler;

  private final JwtAuthenticationProvider authenticationProvider;

  private final JwtAuthenticationSuccessHandler JwtAuthenticationSuccessHandler;

  @Autowired
  public WebSecurityConfig(
      JwtAuthenticationEntryPoint unauthorizedHandler,
      JwtAuthenticationProvider authenticationProvider,
      JwtAuthenticationSuccessHandler JwtAuthenticationSuccessHandler) {
    this.unauthorizedHandler = unauthorizedHandler;
    this.authenticationProvider = authenticationProvider;
    this.JwtAuthenticationSuccessHandler = JwtAuthenticationSuccessHandler;
  }

  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
    authenticationTokenFilter.setAuthenticationManager(authenticationManager());
    authenticationTokenFilter.setAuthenticationSuccessHandler(
        new JwtAuthenticationSuccessHandler());

    return authenticationTokenFilter;
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors()
        .and()
        // we don't need CSRF because our token is invulnerable
        .csrf()
        .disable()
        // All urls must be authenticated (filter for token always fires (/api/**)
        .authorizeRequests()
        .antMatchers("/api/**")
        .authenticated()
        .and()
        // Call our errorHandler if authentication/authorisation fails
        .exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler)
        .and()
        // don't create session (REST)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Custom JWT based security filter
    httpSecurity.addFilterBefore(
        authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    // disable page caching
    httpSecurity.headers().cacheControl();

    httpSecurity
        .formLogin()
        .loginProcessingUrl("/loginForm")
        .successHandler(JwtAuthenticationSuccessHandler)
        .failureHandler(new JwtAuthenticationFailureHandler());
  }

  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    // Default users to grant access
    authenticationManagerBuilder
        .inMemoryAuthentication()
        .withUser("user")
        .password("test123")
        .authorities("USER")
        .and()
        .withUser("admin")
        .password("test123")
        .authorities("ADMIN");

    authenticationManagerBuilder.authenticationProvider(authenticationProvider);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
