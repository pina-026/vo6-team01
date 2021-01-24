package com.example.api;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  // アカウント登録時のパスワードエンコードで利用するためDI管理する。
  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
      .antMatchers("/**").permitAll();
      http
      .csrf().disable();
  }

  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  //     http.authorizeRequests()
  //             .antMatchers("/api/**").permitAll()//apiは許可
  //             .antMatchers("/loginFrom").permitAll()//ログインフォームは許可
  //             .antMatchers("/user/**").permitAll()
  //             .antMatchers("/new").permitAll()//test用(ユーザ登録)※終わったら消す
  //             .antMatchers("/index").permitAll()//test用(ユーザ登録後の遷移画面）※終わったら消す
  //             .antMatchers("/user/create").permitAll()//test用機能※終わったら消す
  //             .anyRequest().authenticated();// それ以外は全て認証無しの場合アクセス不許可
  //     http.formLogin()
  //             .loginProcessingUrl("/login")//ログイン処理をするURL
  //             .loginPage("/loginFrom")//ログイン画面のURL
  //             .failureUrl("/login?error")//認証失敗時のURL
  //             .successForwardUrl("/success")//認証成功時のURL
  //             .usernameParameter("email")//ユーザのパラメータ名
  //             .passwordParameter("password");//パスワードのパラメータ名
  //     http.logout()
  //             .logoutUrl("/logout**")//ログアウト時のURL（今回は未実装）
  //             .logoutSuccessUrl("/login");//ログアウト成功時のURL
  // }
}