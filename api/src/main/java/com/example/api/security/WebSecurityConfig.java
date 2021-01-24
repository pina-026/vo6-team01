package com.example.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth,
      @Qualifier("winterUserDetailsService") UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder) throws Exception {

      auth.eraseCredentials(true)
          .userDetailsService(userDetailsService)
          .passwordEncoder(passwordEncoder);

  }

  // アカウント登録時のパスワードエンコードで利用するためDI管理する。
  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  AuthenticationEntryPoint authenticationEntryPoint() {
    return new WinterAuthenticationEntryPoint();
  }

  AccessDeniedHandler accessDeniedHandler() {
    return new WinterAccessDeniedHandler();
  }

  AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new WinterAuthenticationSuccessHandler();
  }

  AuthenticationFailureHandler authenticationFailureHandler() {
    return new WinterAuthenticationFailureHandler();
  }

  /**
   * 静的ファイルには認証をかけない
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**", "/shutdown" /* for Demo */);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers("/api/**").permitAll();
      http.formLogin()
        .loginProcessingUrl("/api/login").permitAll()
          .usernameParameter("email")
          .passwordParameter("password")
        .successHandler(authenticationSuccessHandler())
        .failureHandler(authenticationFailureHandler())
      .and()
      .logout()
        .logoutUrl("/api/logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");
        // .logoutSuccessHandler(logoutSuccessHandler());

      http.csrf()
        .disable();
      
      http.csrf()
        .ignoringAntMatchers("/api/login");

      // http.csrf()
      //   .csrfTokenRepository(new CookieCsrfTokenRepository());
  }  
}