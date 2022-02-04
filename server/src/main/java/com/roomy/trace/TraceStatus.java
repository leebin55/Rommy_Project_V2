package com.roomy.trace;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;
    private String msg;

    public static TraceStatus getInstance(TraceId traceId, Long time , String msg){
        return new TraceStatus(traceId,time,msg);
    }

}
