package com.roomy.repository;

import com.roomy.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVO,String > {
}
