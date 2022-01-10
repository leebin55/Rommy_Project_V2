package com.roomy.config;



import com.roomy.config.filter.CustomAuthenticationFilter;
import com.roomy.config.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * SecurityContextPersistenceFilter는 이후의 필터동작들이 끝난 후,
     * 다시 자신의 실행흐름으로 돌아와,
     * 인증 완료된 Authentication 객체가 존재할 경우,
     * 이를 SecurityContextRepository에 저장합니다.(기본 구현은 in memory)
     */
    private final UserDetailsService userDetailsService; // springframework.security 가 제공 (Bean 따로 등록 안해도 됨)
    private final PasswordEncoder bCryptPasswordEncoder;

    // Bean으로 PasswordEncoder interface를 등록
    // BVryptPasswordEncoder 는 PasswordEncoder  interface를 implements 한다.
    public SpringSecurityConfig(UserDetailsService userDetailsService
            , PasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Authentication 로그인 관련
        // UserDetailsService 와 BCrypt... 를 사용하겠다.
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl("/api/login"); // url 변경
        //토큰이 없는 상태로 들어올때는 permit 해주기
       http
               .authorizeRequests() // HttpServletRequest 를 사용하는 요청들에대한 접근제한을 설정하겠다
               .antMatchers("/*").permitAll();

//                .anyRequest() // 나머지 요청들에 대해서는
//                .authenticated();// 인증받아야 한다.
        // token을 사용하는 방식이기 때문에 csrf를 disable
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()

        // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//----------------------------------------------------------------------------
        // filter 추가 > authentication filter 사용자 확인 (로그인 할때마다 알림)
        // customizing 한 filter 사용
        //http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilter(authenticationFilter);
        // addFilterBefore : we need to intercept all requests before any other filters
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
//---------------------빈등록------------------------------------------------
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
////        bean.setPasswordEncoder(passwordEncoder());
//        return bean;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 암호화 하는 알고리즘은 여b러가지 존재 (SHA256,scrypt,pbkdf2...)
        // 기본 해시 함수인 SHA, MD5등을 이용하는 것은 빠른 처리속도로 인하여 보안에 취약.
        //해시를 강하게 하기 위해서는 무작위 salt와 함께 반복적인 해싱작업이 필요하다.
        //구현이 쉽고 비교적 위의 알고릴즘보다 강력한 BCrypt 를 이용
        // 만약에 여러 이미 다른 알고리즘으로 암호화를 저장되어있지만 더 고도화된 암호화를 하고 싶다면
        // DelegatingPasswordEncoder 를 이용하자
        return new BCryptPasswordEncoder();
    }
}
