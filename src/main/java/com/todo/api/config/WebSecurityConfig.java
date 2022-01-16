package com.todo.api.config;

import com.todo.api.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http 시큐리티 빌더
        http.cors() // WebMvcConfig 에서 이미 설정했으르로 기본 cors 설정
                .and()
                .csrf()
                    .disable()
                .httpBasic() // token을 사용하므로 basic 인증은 disable
                    .disable()
                .sessionManagement() // session 기반이 아님을 선언
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/", "/auth/**").permitAll()
                .anyRequest()
                    .authenticated();

        // filter 등록
        // 매 요청마다 CorsFilter 실행 후 jwtAuthenticationFiler 실행
        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
    }
}
