package com.roomy.repository.qrepo;

import com.roomy.dto.user.UserWithBoardDTO;

public interface BoardRepositoryCustom {

    public UserWithBoardDTO loadBoardDetail(Long boardSeq);
}
