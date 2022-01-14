package com.roomy.config.JWT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 필터에서 예외가 발생한 경우,
 * 필터의 순서상 Spring의 DispatcherServlet까지 예외가 도달하지 않기 때문에
 * (Spring security filter 는 DispatcherServlet 전 //  후는 interceptor)
 * 이를 처리하기위한 Handler
 */
@Component @Slf4j
public class JwtEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("UnAuth error :{}", authException.getMessage());
        //포워딩 처리
//    response.sendRedirect("/auth/exception");
    // 유효한 자격증명을 제공하지 않고 접근하려 할때 401 Unauthorized 에러를 리턴할 클래스
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED );
    }

}
