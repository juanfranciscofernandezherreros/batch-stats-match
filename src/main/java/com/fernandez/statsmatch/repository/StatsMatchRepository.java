package com.fernandez.statsmatch.repository;

import com.fernandez.statsmatch.model.entity.StatsMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StatsMatchRepository extends JpaRepository<StatsMatch,Long> {
}
