package com.roomy.model;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Birth {
    /** 생성자에서 값을 초기화하고 변경 불가능하게
     * > 생년월일은 바뀌지 않으므로 처음에 생성될때 값을 세팅*/

    private String year;
    private String month;
    private String day;

    // 기본생성자  생성 이유 :  JPA 스펙상 기본생성자가 있어야 됨
    // 하지만  setter  를 생성안하고 생성 될때 값을 세팅 해줄 예정
    // => 상속받은 클래스만 부를 수 있게 protected 로 생성
    protected Birth(){

    }

    public Birth(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
