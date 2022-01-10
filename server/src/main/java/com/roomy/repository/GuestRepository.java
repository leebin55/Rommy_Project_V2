package com.roomy.repository;

import com.roomy.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}
