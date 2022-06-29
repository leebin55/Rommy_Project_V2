package com.roomy.config;


import com.roomy.jwt.JwtAccessDeniedHandler;
import com.roomy.jwt.JwtEntryPoint;
import com.roomy.jwt.TokenProvider;
import com.roomy.jwt.CustomJwtFilter;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter; // 다른 패키지로 import 하면 빈인식못함



 @EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * SecurityContextPersistenceFilter는 이후의 필터동작들이 끝난 후,
     * 다시 자신의 실행흐름으로 돌아와,
     * 인증 완료된 Authentication 객체가 존재할 경우,
     * 이를 SecurityContextRepository에 저장.(기본 구현은 in memory)
     */

    private final TokenProvider tokenProvider;
    private final JwtEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CorsFilter corsFilter;



    public SpringSecurityConfig(TokenProvider tokenProvider,
                                JwtEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler,
                                CorsFilter corsFilter) {

        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;

        this.corsFilter = corsFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/auth/login","/auth/refresh").permitAll()
                .antMatchers("/users/sign_up","/users/valid/**").permitAll()
                .antMatchers("/feeds").permitAll()
                .antMatchers("/top-rooms").permitAll()
                .antMatchers("/uploads/*").permitAll() // img
                .anyRequest()
                .authenticated();

        http
                .csrf().disable()
                .addFilterBefore(corsFilter,UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and().
                addFilterBefore(new CustomJwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .antMatchers("/img/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
