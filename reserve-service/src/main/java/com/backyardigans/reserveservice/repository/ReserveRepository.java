package com.backyardigans.reserveservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backyardigans.reserveservice.domain.Reserve;


public interface ReserveRepository extends JpaRepository<Reserve, String>{
    @Query("SELECT r FROM reserve r WHERE r.initAt < :finishAt AND r.finishAt > :initAt")
    List<Reserve> findOverlappingReserves(
            @Param("initAt") LocalDateTime initAt,
            @Param("finishAt") LocalDateTime finishAt
    );

    @Query("SELECT r FROM reserve r WHERE r.finishAt < CURRENT_TIMESTAMP AND r.status != DONE")
    List<Reserve> findMissed();

}
