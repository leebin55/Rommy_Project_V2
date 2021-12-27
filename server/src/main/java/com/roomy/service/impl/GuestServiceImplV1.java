package com.roomy.service.impl;

import com.roomy.model.GuestVO;
import com.roomy.model.board.Board;
import com.roomy.repository.GuestRepository;
import com.roomy.service.GuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service("guestService")
public class GuestServiceImplV1 implements GuestService {

    private final GuestRepository guestRepository;

    public GuestServiceImplV1(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }


    @Override
    public List<GuestVO> selectAll() {
        return null;
    }

    @Override
    public Board findById(Long aLong) {
        return null;
    }

    @Override
    public void insert(GuestVO guestVO) {

    }

    @Override
    public void update(GuestVO guestVO) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<GuestVO> mainList(String userId) {
        return null;
    }

    @Override
    public List<GuestVO> allList(String userId) {
        return null;
    }

    @Override
    public String findWriter(Long guestSeq) {
        return null;
    }
}
