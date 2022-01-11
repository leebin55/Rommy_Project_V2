package com.roomy.config;



import com.roomy.config.JWT.TokenProvider;
import com.roomy.config.filter.CustomAuthenticationFilter;
import com.roomy.config.filter.CustomJwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    // Bean으로 PasswordEncoder interface를 등록
    // BVryptPasswordEncoder 는 PasswordEncoder  interface를 implements 한다.
    public SpringSecurityConfig(UserDetailsService userDetailsService
            , BCryptPasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
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
        authenticationFilter.setFilterProcessesUrl("/user/login"); // url 변경
        //토큰이 없는 상태로 들어올때는 permit 해주기
       http
               .authorizeRequests() // HttpServletRequest 를 사용하는 요청들에대한 접근제한을 설정하겠다
               .antMatchers("/*").permitAll();

//                .anyRequest() // 나머지 요청들에 대해서는
//                .authenticated();// 인증받아야 한다.
        // token을 사용하는 방식이기 때문에 csrf를 disable
        http
                .csrf().disable();

        // 세션을 사용하지 않기 때문에 STATELESS로 설정
                http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().
                antMatchers("/user/login/**","/token/refresh/**")
                .permitAll();// login 부분
//----------------------------------------------------------------------------
        // filter 추가 > authentication filter 사용자 확인 (로그인 할때마다 알림)
        // customizing 한 filter 사용
        //http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilter(authenticationFilter);
        // addFilterBefore : we need to intercept all requests before any other filters
        http.addFilterBefore(new CustomJwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
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


}
