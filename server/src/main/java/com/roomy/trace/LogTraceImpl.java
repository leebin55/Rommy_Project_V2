package com.roomy.trace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogTraceImpl implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        syncTraceId(); // 전역변수의 동시성 문제 때문에
        TraceId traceId = traceIdHolder.get();
        Long starTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return TraceStatus.getInstance(traceId,starTimeMs,message);
    }

    private Object addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <level ; i++){
            sb.append((i == level -1 )? "|" + prefix : "|     ");
        }
        return sb.toString();
    }

    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();
        if(traceId == null){
            traceIdHolder.set(new TraceId());
        }else{
            traceIdHolder.set(traceId.createNextId());
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status,null);
    }

    private void complete(TraceStatus status, Exception e) {
         Long stopTimeMs = System.currentTimeMillis();
         Long resultTime = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if(e == null){
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel())
                    , status.getMsg(), resultTime);
        }else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),
                    status.getMsg(), resultTime, e.toString());
        }
        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();
        if(traceId.isFirstLevel()){
            traceIdHolder.remove(); // 꼭 직접 remove 해줘야!
        }else {
            traceIdHolder.set(traceId.createPrevId());
        }
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status,e);
    }
}
