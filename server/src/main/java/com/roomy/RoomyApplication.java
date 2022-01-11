package com.roomy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableCaching// 설정해놓은 cache를 사용하겠다는 정의
@SpringBootApplication
public class RoomyApplication {

	public static void main(String[] args) {

		SpringApplication.run(RoomyApplication.class, args);

	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		// 암호화 하는 알고리즘은 여b러가지 존재 (SHA256,scrypt,pbkdf2...)
		// 기본 해시 함수인 SHA, MD5등을 이용하는 것은 빠른 처리속도로 인하여 보안에 취약.
		//해시를 강하게 하기 위해서는 무작위 salt와 함께 반복적인 해싱작업이 필요하다.
		//구현이 쉽고 비교적 위의 알고릴즘보다 강력한 BCrypt 를 이용
		// 만약에 여러 이미 다른 알고리즘으로 암호화를 저장되어있지만 더 고도화된 암호화를 하고 싶다면
		// DelegatingPasswordEncoder 를 이용하자
		return new BCryptPasswordEncoder();
	}


}
