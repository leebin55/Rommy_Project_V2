package com.roomy.aspect;

import com.roomy.dto.user.UserWithRoomDTO;
import com.roomy.exception.ResourceNotFoundException;
import com.roomy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
@Slf4j
@Aspect
public class RoomValidateAspect {

    private final UserRepository userRepository;

    @Around("execution(* com.roomy.controller.*Room*.*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        Object[] args = joinPoint.getArgs();
        log.info("room exception 처리 : {}", args.toString());
        Arrays.stream(args).forEach((arg)->{
            log.info("{} param 으로 들어온 값 {}",joinPoint.getSignature().toShortString(),arg.toString());
        });
        String username = (String) args[0];
        Long roomId = (Long) args[1];
        UserWithRoomDTO user = userRepository.userWithRoomByUsername(username).orElse(null);
        if(user == null){
            throw new ResourceNotFoundException("해당 회원이 존재하지 않습니다.");

        }
        if(user.getRoomId() != roomId){
            throw new ResourceNotFoundException("해당 회원의 ROOM 번호와 일치하지 않습니다.");
        }

        return joinPoint.proceed();

    }
}
